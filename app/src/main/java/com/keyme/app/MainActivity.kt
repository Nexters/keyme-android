package com.keyme.app

import android.animation.ObjectAnimator
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.keyme.app.ui.KeymeApp
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.usecase.GetMyUserInfoUseCase
import com.keyme.domain.usecase.GetPushTokenSavedStateUseCase
import com.keyme.domain.usecase.InsertPushTokenUseCase
import com.keyme.domain.usecase.SetPushTokenSavedStateUseCase
import com.keyme.presentation.UiEvent
import com.keyme.presentation.UiEventManager
import com.keyme.presentation.utils.FcmUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var getMyUserInfoUseCase: GetMyUserInfoUseCase

    @Inject
    lateinit var uiEventManager: UiEventManager

    @Inject
    lateinit var insertPushTokenUseCase: InsertPushTokenUseCase

    @Inject
    lateinit var getPushTokenSavedStateUseCase: GetPushTokenSavedStateUseCase

    @Inject
    lateinit var setPushTokenSavedStateUseCase: SetPushTokenSavedStateUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSplashScreen()

        setContent {
            KeymeApp()
        }

        handleUiEvent()
        askNotificationPermission()
        checkUnsavedPushTokenExistence()

        lifecycleScope.launch {
            Timber.d("Fcm Token: ${FcmUtil.getToken()}")
        }
    }

    private fun setSplashScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat(),
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 200L

                slideUp.doOnEnd { splashScreenView.remove() }

                slideUp.start()
            }
        }
    }

    private fun handleUiEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                uiEventManager.uiEvent.collectLatest {
                    when (it) {
                        is UiEvent.Toast -> {
                            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
    }

    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun checkUnsavedPushTokenExistence() {
        lifecycleScope.launch {
            if (!getMyUserInfoUseCase().first()?.accessToken.isNullOrBlank()) {
                val isPushTokenSaved = getPushTokenSavedStateUseCase.invoke()
                if (!isPushTokenSaved) {
                    FcmUtil.getToken()?.let {
                        insertPushTokenUseCase.invoke(it)
                            .onSuccess { setPushTokenSavedStateUseCase.invoke(true) }
                    }
                }
            }
        }
    }
}
