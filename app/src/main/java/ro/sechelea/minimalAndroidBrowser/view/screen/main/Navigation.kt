package ro.sechelea.minimalAndroidBrowser.view.screen.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ro.sechelea.minimalAndroidBrowser.view.screen.NavigationDestination
import ro.sechelea.minimalAndroidBrowser.view.screen.NavigationDestination.CHECK_PASSWORD
import ro.sechelea.minimalAndroidBrowser.view.screen.NavigationDestination.ERROR
import ro.sechelea.minimalAndroidBrowser.view.screen.NavigationDestination.HOME
import ro.sechelea.minimalAndroidBrowser.view.screen.NavigationDestination.SETTINGS
import ro.sechelea.minimalAndroidBrowser.view.screen.NavigationDestination.WEBVIEW
import ro.sechelea.minimalAndroidBrowser.view.screen.UiScreen
import ro.sechelea.minimalAndroidBrowser.view.screen.settings.CheckPasswordScreen
import ro.sechelea.minimalAndroidBrowser.view.screen.settings.SettingsScreen
import ro.sechelea.minimalAndroidBrowser.view.screen.web.ErrorScreen
import ro.sechelea.minimalAndroidBrowser.view.screen.web.HomeScreen
import ro.sechelea.minimalAndroidBrowser.view.screen.web.WebViewScreen

class Navigation(
    private val navController: NavHostController,
    private val initialUrl: String?,
    private val updateTitle: (String) -> Unit,
    private val innerPadding: PaddingValues
) : UiScreen {
    companion object {
        private const val PASSWORD_CHECKED = "passwordChecked"
        private const val ERROR_TEXT = "errorText"
    }

    @Composable
    override fun Show() {
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = getDestination(initialUrl).destination
        ) {
            composable(HOME.destination) {
                updateTitle("Home")
                HomeScreen().Show()
            }
            composable(WEBVIEW.destination) {
                updateTitle("Loading...")
                WebViewScreen(initialUrl!!, updateTitle, navController).Show()
            }
            composable("${ERROR.destination}?$ERROR_TEXT={$ERROR_TEXT}",
                arguments = listOf( navArgument(ERROR_TEXT) { type = NavType.StringType } )
            ) {
                updateTitle("Error")
                val errorText: String? = it.arguments?.getString(ERROR_TEXT)
                ErrorScreen(navController, errorText).Show()
            }
            composable(CHECK_PASSWORD.destination) {
                updateTitle("Settings")
                CheckPasswordScreen(navController).Show()
            }
            composable("${SETTINGS.destination}/{$PASSWORD_CHECKED}",
                arguments = listOf( navArgument(PASSWORD_CHECKED) { type = NavType.BoolType } )
            ) {
                updateTitle("Settings")
                val passwordChecked: Boolean = it.arguments?.getBoolean(PASSWORD_CHECKED) ?: false
                SettingsScreen(navController, passwordChecked).Show()
            }
        }
    }

    private fun getDestination(incomingUrl: String?): NavigationDestination {
        return when {
            incomingUrl.isNullOrEmpty() -> HOME
            isValidUrl(incomingUrl) -> WEBVIEW
            else -> ERROR
        }
    }

    private fun isValidUrl(incomingUrl: String): Boolean {
        return incomingUrl.matches("https?://\\S*".toRegex())
    }

}
