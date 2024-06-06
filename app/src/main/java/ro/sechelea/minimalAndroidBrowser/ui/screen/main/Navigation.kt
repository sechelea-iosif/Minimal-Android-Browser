package ro.sechelea.minimalAndroidBrowser.ui.screen.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ro.sechelea.minimalAndroidBrowser.ui.screen.NavigationDestination
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen
import ro.sechelea.minimalAndroidBrowser.ui.screen.settings.SettingsPasswordScreen
import ro.sechelea.minimalAndroidBrowser.ui.screen.settings.SettingsScreen
import ro.sechelea.minimalAndroidBrowser.ui.screen.web.ErrorScreen
import ro.sechelea.minimalAndroidBrowser.ui.screen.web.HomeScreen
import ro.sechelea.minimalAndroidBrowser.ui.screen.web.WebViewScreen

class Navigation(
    private val navController: NavHostController,
    private val initialUrl: String?,
    private val updateTitle: (String) -> Unit,
    private val innerPadding: PaddingValues
) : UiScreen {

    @Composable
    override fun Show() {
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = getDestination(initialUrl).destination
        ) {
            composable(NavigationDestination.HOME.destination) {
                updateTitle("Home")
                HomeScreen().Show()
            }
            composable(NavigationDestination.WEBVIEW.destination) {
                WebViewScreen(initialUrl!!, updateTitle).Show()
            }
            composable(NavigationDestination.ERROR.destination) {
                updateTitle("Error")
                ErrorScreen(initialUrl).Show()
            }
            composable(NavigationDestination.SETTINGS_PASSWORD.destination) {
                updateTitle("Settings")
                SettingsPasswordScreen(navController).Show()
            }
            composable(NavigationDestination.SETTINGS.destination) {
                updateTitle("Settings")
                SettingsScreen(navController).Show()
            }
        }
    }

    private fun getDestination(incomingUrl: String?): NavigationDestination {
        return if (incomingUrl.isNullOrEmpty()) NavigationDestination.HOME
        else if (isValidUrl(incomingUrl)) NavigationDestination.WEBVIEW
        else NavigationDestination.ERROR
    }

    private fun isValidUrl(incomingUrl: String): Boolean {
        return incomingUrl.matches("https?://\\S*".toRegex())
    }

}
