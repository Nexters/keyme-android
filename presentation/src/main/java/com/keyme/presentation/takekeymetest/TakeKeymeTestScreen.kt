package com.keyme.presentation.takekeymetest

import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState
import timber.log.Timber

@Composable
fun TakeKeymeTestScreen(
    loadTestUrl: String,
    onTestSolved: (String) -> Unit,
    onBackClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    val state = rememberWebViewState(url = loadTestUrl)
    val navigator = rememberWebViewNavigator()

    BackHandler {
        // do nothing
    }

    WebView(
        modifier = Modifier.fillMaxSize(),
        state = state,
        captureBackPresses = true,
        client = MyWebViewerClient(),
        navigator = navigator,
        onCreated = {
            it.apply {
                setNetworkAvailable(true)
                settings.loadWithOverviewMode = true
                settings.useWideViewPort = true
                settings.javaScriptEnabled = true
                it.addJavascriptInterface(
                    TakeKeymeTestInterface(
                        onSolved = {

                        },
                        onClose = {

                        },
                    ),
                    "KeymeAndroid",
                )
                settings.domStorageEnabled = true
                settings.setSupportZoom(false)
            }
        },
    )
}

private class TakeKeymeTestInterface(
    private val onSolved: (String) -> Unit,
    private val onClose: () -> Unit,
) {
    @JavascriptInterface
    fun onTestSolved(resultCode: String) {
        Timber.d("onTestSolved(resultCode: $resultCode)")
        onSolved(resultCode)
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

