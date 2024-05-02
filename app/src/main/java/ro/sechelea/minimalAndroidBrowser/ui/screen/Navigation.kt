package ro.sechelea.minimalAndroidBrowser.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class Navigation(
    private val url: String?
): UiScreen {
    @Composable
    override fun Show() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen(navController, url).Show()
            }
        }
    }
}
