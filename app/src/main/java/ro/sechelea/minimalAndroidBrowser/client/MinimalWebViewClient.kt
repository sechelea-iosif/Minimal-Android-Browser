package ro.sechelea.minimalAndroidBrowser.client

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import ro.sechelea.minimalAndroidBrowser.base.stripUrlBeforeSecondLevelDomain


class MinimalWebViewClient(
    private val initialWebUrl: String
) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return when {
            request == null -> true
            request.isRedirect -> false
            else -> shouldBlockUrl(request.url.toString())
        }
    }

    private fun shouldBlockUrl(url: String): Boolean {
        return url.stripUrlBeforeSecondLevelDomain() != initialWebUrl.stripUrlBeforeSecondLevelDomain()
    }
}
