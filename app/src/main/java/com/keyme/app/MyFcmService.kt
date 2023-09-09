package com.keyme.app

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.keyme.domain.entity.onFailure
import com.keyme.domain.entity.onSuccess
import com.keyme.domain.usecase.GetMyUserInfoUseCase
import com.keyme.domain.usecase.InsertPushTokenUseCase
import com.keyme.domain.usecase.SetPushTokenSavedStateUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MyFcmService : FirebaseMessagingService() {

    @Inject
    lateinit var getMyUserInfoUseCase: GetMyUserInfoUseCase

    @Inject
    lateinit var insertPushTokenUseCase: InsertPushTokenUseCase

    @Inject
    lateinit var setPushTokenSavedStateUseCase: SetPushTokenSavedStateUseCase

    private val insertPushTokenJob = SupervisorJob()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("remoteMessage: $remoteMessage")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("newToken: $token")
        CoroutineScope(insertPushTokenJob).launch {
            if (!getMyUserInfoUseCase().first()?.accessToken.isNullOrBlank()) {
                insertPushTokenUseCase.invoke(token)
                    .onSuccess {
                        setPushTokenSavedStateUseCase.invoke(true)
                        insertPushTokenJob.cancel()
                    }
                    .onFailure {
                        setPushTokenSavedStateUseCase.invoke(false)
                        insertPushTokenJob.cancel()
                    }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        insertPushTokenJob.cancel()
    }
}
