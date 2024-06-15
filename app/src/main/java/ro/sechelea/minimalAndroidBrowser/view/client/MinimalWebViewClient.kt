package ro.sechelea.minimalAndroidBrowser.view.client

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MinimalWebViewClient(
    private val onValueChange: (url: String, isRedirect: Boolean?) -> Unit
) : WebViewClient() {

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        if (url != null) { onValueChange(url, null) }
        super.doUpdateVisitedHistory(view, url, isReload)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        request?.apply { onValueChange(url.toString(), request.isRedirect) }
        return request == null
    }
}
