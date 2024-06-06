package ro.sechelea.minimalAndroidBrowser.ui.client

import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MinimalWebViewClient(
    private val initialWebUrl: String,
    private val onValueChange: (String) -> Unit
) : WebViewClient() {

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        Log.i(MinimalWebViewClient::class.toString(), "doUpdateVisitedHistory : " + url.toString())
        if (url != null) {
            if (shouldBlockUrl(url)) {
                view?.loadUrl(initialWebUrl)
            }
            onValueChange(url)
        }
        super.doUpdateVisitedHistory(view, url, isReload)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        Log.i(MinimalWebViewClient::class.toString(), "shouldOverride Request : " + request?.url.toString())
        return when {
            request == null -> true
            request.isRedirect -> false
            else -> shouldBlockUrl(request.url.toString())
        }
    }

    private fun shouldBlockUrl(url: String): Boolean {
        val newUrl = stripUrlBeforeSecondLevelDomain(url)
        val initialUrl = stripUrlBeforeSecondLevelDomain(initialWebUrl)
        if (newUrl == null || initialUrl == null) {
            return true
        }
        if (newUrl == initialUrl) return false
        if (newUrl.startsWith(initialUrl)) {
            val endOfNewUrl = newUrl.removePrefix(initialUrl)
            return !(endOfNewUrl.startsWith("?") || endOfNewUrl.startsWith("#"))
        } else return true
    }

    private fun stripUrlBeforeSecondLevelDomain(url: String): String? {
        val processedUrl = url.lowercase().trim() + "/"
        val regex = "[^./]*\\.[^.]*/.*".toRegex()
        val match = regex.find(processedUrl)?.value
        val trimmed = match?.dropLast(1)
        return when {
            trimmed == null -> null
            !trimmed.contains("/") -> "$trimmed/"
            else -> trimmed
        }
    }

}
