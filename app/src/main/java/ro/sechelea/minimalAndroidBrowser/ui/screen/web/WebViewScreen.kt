package ro.sechelea.minimalAndroidBrowser.ui.screen.web

import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import ro.sechelea.minimalAndroidBrowser.client.MinimalWebViewClient
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen

class WebViewScreen(
    private val webUrl: String,
    private val updateTitle: (String) -> Unit,
) : UiScreen {

    @Composable
    override fun Show() {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            update = { it.loadUrl(webUrl) },
            factory = { WebView(it).apply {
                webViewClient = MinimalWebViewClient(webUrl, updateTitle)
                settings.applyWebSettings()
            } }
        )
    }
}

private fun WebSettings.applyWebSettings() = apply {
    // security
    safeBrowsingEnabled = true
    // js
    javaScriptEnabled = true
    domStorageEnabled = true
    javaScriptCanOpenWindowsAutomatically = false
//    setSupportMultipleWindows(false)
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
