package ro.sechelea.minimalAndroidBrowser.ui

import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import ro.sechelea.minimalAndroidBrowser.client.MinimalWebViewClient

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
fun AndroidWebView(
    webUrl: String,
    onValueChange: (String) -> Unit
) {
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = MinimalWebViewClient(webUrl, onValueChange)
            settings.apply {
                // security
                safeBrowsingEnabled = true
                // js
                javaScriptEnabled = true
                domStorageEnabled = true
                javaScriptCanOpenWindowsAutomatically = false
//                setSupportMultipleWindows(false)
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
            }
        }
    }, update = {
        it.loadUrl(webUrl)
    })
}