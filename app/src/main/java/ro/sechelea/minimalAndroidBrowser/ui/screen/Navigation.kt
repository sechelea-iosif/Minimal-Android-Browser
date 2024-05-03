package ro.sechelea.minimalAndroidBrowser.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ro.sechelea.minimalAndroidBrowser.ui.screen.Navigation.NavigationDestinations.ERROR
import ro.sechelea.minimalAndroidBrowser.ui.screen.Navigation.NavigationDestinations.MAIN
import ro.sechelea.minimalAndroidBrowser.ui.screen.Navigation.NavigationDestinations.WEBVIEW

class Navigation(
    private val incomingUrl: String?
): UiScreen {
    @Composable
    override fun Show() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = getDestination(incomingUrl).destination
        ) {
            composable(MAIN.destination) {
                MainScreen().Show()
            }
            composable(WEBVIEW.destination) {
                WebViewScreen(incomingUrl!!).Show()
            }
            composable(ERROR.destination) {
                ErrorScreen(incomingUrl).Show()
            }
        }
    }

    private fun getDestination(incomingUrl: String?): NavigationDestinations {
        return if (incomingUrl == null) MAIN
        else if (isValidUrl(incomingUrl)) WEBVIEW
        else ERROR
    }

    private fun isValidUrl(incomingUrl: String): Boolean {
        return incomingUrl.matches("https?://\\S*".toRegex())
    }

    private enum class NavigationDestinations(val destination: String) {
        MAIN("main"), ERROR("error"), WEBVIEW("webview")
    }
}
