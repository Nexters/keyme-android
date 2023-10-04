package com.keyme.presentation.takekeymetest

import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import com.google.gson.Gson
import com.keyme.domain.entity.response.TestRegisterResponse
import com.keyme.presentation.designsystem.theme.keyme_black
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun TakeKeymeTestScreen(
    loadTestUrl: String,
    accessToken: String,
    onTestSolved: (TestRegisterResponse) -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    Timber.d("loadTestUrl: $loadTestUrl")

    val state = rememberWebViewState(url = loadTestUrl)
//    val state = rememberWebViewState(url = "file:///android_asset/sample.html")
    val navigator = rememberWebViewNavigator()

    BackHandler {
        // do nothing
    }

    val coroutineScope = rememberCoroutineScope()
    if (accessToken.isNotBlank()) {
        WebView(
            modifier = Modifier.fillMaxSize(),
            state = state,
            captureBackPresses = true,
            client = MyWebViewerClient(),
            navigator = navigator,
            onCreated = {
                it.apply {
                    setBackgroundColor(keyme_black.toArgb())
                    setNetworkAvailable(true)
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    settings.javaScriptEnabled = true
                    settings.userAgentString = "KEYME_$accessToken"
                    it.addJavascriptInterface(
                        TakeKeymeTestInterface(
                            onSolved = {
                                coroutineScope.launch {
                                    onTestSolved(it)
                                }
                            },
                            onClose = {
                                coroutineScope.launch {
                                    onCloseClick()
                                }
                            },
                        ),
                        "keymeAndroid",
                    )
                    settings.domStorageEnabled = true
                    settings.setSupportZoom(false)
                }
            },
        )
    }
}

private class TakeKeymeTestInterface(
    private val onSolved: (TestRegisterResponse) -> Unit,
    private val onClose: () -> Unit,
) {
    @JavascriptInterface
    fun onTestSolved(result: String) {
        Timber.d("onTestSolved(result: $result)")

        val testResult = kotlin.runCatching {
            Gson().fromJson(result, TestRegisterResponse::class.java)
        }.getOrNull()

        testResult?.let { onSolved(testResult) }
    }

    @JavascriptInterface
    fun onCloseClick() {
        Timber.d("onCloseClick()")
        onClose()
    }
}

private class MyWebViewerClient : AccompanistWebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return request?.url?.let { uri ->
            val context = view?.context ?: return false

            when (uri.scheme) {
                "intent" -> {
                    val intent = Intent.parseUri(uri.toString(), Intent.URI_INTENT_SCHEME)
                    val installedPackage =
                        context.packageManager.getLaunchIntentForPackage(context.packageName)

                    if (installedPackage != null) {
                        context.startActivity(intent)
                        true
                    } else {
                        val marketIntent = Intent(Intent.ACTION_VIEW).apply {
                            data = Uri.parse("market://details?id=" + intent.getPackage())
                        }
                        context.startActivity(marketIntent)
                        true
                    }
                }

                "market" -> {
                    val marketIntent = Intent(Intent.ACTION_VIEW).apply {
                        data = uri
                    }
                    context.startActivity(marketIntent)
                    true
                }

                else -> false
            }
        } ?: false
    }
}
