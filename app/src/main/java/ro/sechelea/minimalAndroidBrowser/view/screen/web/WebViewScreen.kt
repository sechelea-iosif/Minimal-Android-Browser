package ro.sechelea.minimalAndroidBrowser.view.screen.web

import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ro.sechelea.minimalAndroidBrowser.model.domain.WebsiteUrl
import ro.sechelea.minimalAndroidBrowser.view.client.MinimalWebViewClient
import ro.sechelea.minimalAndroidBrowser.view.screen.NavigationDestination.Companion.ERROR
import ro.sechelea.minimalAndroidBrowser.view.screen.UiScreen
import ro.sechelea.minimalAndroidBrowser.viewmodel.BlacklistViewModel
import java.util.Optional

class WebViewScreen(
    private var initialWebUrl: String,
    private val updateTitle: (String) -> Unit,
    private val navController: NavHostController,
) : UiScreen {
    private lateinit var blacklistViewModel: BlacklistViewModel

    @Composable
    override fun Show() {
        // initialized as early as possible, for later use
        blacklistViewModel = viewModel()
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            update = { it.loadUrl(initialWebUrl) },
            factory = { WebView(it).apply {
                webViewClient = MinimalWebViewClient(::onWebViewNavigation)
                settings.applyWebSettings()
            } }
        )
    }

    private fun onWebViewNavigation(url: String, isRedirect: Boolean?) {
        try {
            if (isRedirect == true) {
                initialWebUrl = url
            } else {
                tryBlock(url)
            }
            tryRestrict(url)
            updateTitle(url)
        } catch (exception: IllegalArgumentException) {
            navController.navigate(ERROR(exception.message ?: "Unknown Error"))
        }
    }

    private fun tryRestrict(url: String) {
        val blacklistFlow = blacklistViewModel.getAll()
        val blacklist: List<WebsiteUrl>  = runBlocking { blacklistFlow.first() }
        blacklist.stream()
            .map(WebsiteUrl::url)
            .filter { blocked -> url.matches(".*$blocked.*".toRegex()) }
            .findAny().throwIfPresent { matched ->
                IllegalArgumentException("URL blacklisted by \"$matched\": \"$url\"")
            }
    }

    private fun tryBlock(url: String) {
        val newUrl = stripUrlBeforeSecondLevelDomain(url)
        val initialUrl = stripUrlBeforeSecondLevelDomain(initialWebUrl)

        if (newUrl == null || initialUrl == null)
            throw IllegalArgumentException("Invalid Url")
        if (newUrl == initialUrl) return

        if (newUrl.startsWith(initialUrl)) {
            val endOfNewUrl = newUrl.removePrefix(initialUrl)
            if (endOfNewUrl.startsWith("?") || endOfNewUrl.startsWith("#")) {
                return
            } else throw IllegalArgumentException("Navigation to page disabled: $newUrl")
        } else throw IllegalArgumentException("Navigation to page disabled: $newUrl")
    }

    companion object {
        fun stripUrlBeforeSecondLevelDomain(url: String): String? {
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

        private fun <T> Optional<T>.throwIfPresent(function: (T) -> Exception) {
            this.ifPresent { throw function(this.get()) }
        }

        private fun WebSettings.applyWebSettings() = apply {
            // security
            safeBrowsingEnabled = true
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
        }
    }
}
