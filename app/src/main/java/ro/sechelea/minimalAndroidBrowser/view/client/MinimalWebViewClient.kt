package ro.sechelea.minimalAndroidBrowser.view.client

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MinimalWebViewClient(
    private val onValueChange: (String) -> Unit
) : WebViewClient() {

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        if (url != null) { onValueChange(url) }
        super.doUpdateVisitedHistory(view, url, isReload)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        request?.apply { onValueChange(url.toString()) }
        return request == null
    }
}
