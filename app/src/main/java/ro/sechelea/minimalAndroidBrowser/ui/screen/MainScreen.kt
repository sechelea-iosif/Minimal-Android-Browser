package ro.sechelea.minimalAndroidBrowser.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import ro.sechelea.minimalAndroidBrowser.ui.Background

class MainScreen(
    private val navController: NavHostController
) : UiScreen{
    @Composable
    override fun Show() {
        Background {
            Greeting()
        }
    }

    @Composable
    private fun Greeting(
        modifier: Modifier = Modifier,
        name: String = "World",
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}
