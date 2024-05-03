package ro.sechelea.minimalAndroidBrowser.ui

import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Background(
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        content()
    }
}

@Composable
fun CenteredColumn(
    content: @Composable () -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        content()
    }
}

@Composable
fun AndroidWebView(webUrl: String) {
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.apply {
                // js
                javaScriptEnabled = true
                domStorageEnabled = true
                javaScriptCanOpenWindowsAutomatically = false
                // network
                allowContentAccess = true
                allowFileAccess = false
                blockNetworkImage = false
                blockNetworkLoads = false
                loadsImagesAutomatically = true
                offscreenPreRaster = true
                // accessibility
                builtInZoomControls = false
                displayZoomControls = false
                loadWithOverviewMode = true
                mediaPlaybackRequiresUserGesture = true
                mixedContentMode = WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
                useWideViewPort = true
                // security
                safeBrowsingEnabled = true
            }
        }
    }, update = {
        it.loadUrl(webUrl)
    })
}