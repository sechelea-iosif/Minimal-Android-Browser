package ro.sechelea.minimalAndroidBrowser.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ro.sechelea.minimalAndroidBrowser.ui.Background

class MainScreen(
    private val navController: NavHostController,
    private val intentUrl: String?
) : UiScreen{
    @Composable
    override fun Show() {
        Background {
            if (intentUrl == null ) {
                Text(text = "App was opened from main screen.")
            } else {
                Text(text = "You are trying to access \" $intentUrl \"")
            }
        }
    }
}
