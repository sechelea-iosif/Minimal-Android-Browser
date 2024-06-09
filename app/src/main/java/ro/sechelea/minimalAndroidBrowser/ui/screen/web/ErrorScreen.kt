package ro.sechelea.minimalAndroidBrowser.ui.screen.web

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import ro.sechelea.minimalAndroidBrowser.ui.Background
import ro.sechelea.minimalAndroidBrowser.ui.CenteredPaddedColumn
import ro.sechelea.minimalAndroidBrowser.ui.screen.NavigationDestination.HOME
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen

class ErrorScreen(
    private val navController: NavHostController,
    private val errorText: String?
): UiScreen {
    @Composable
    override fun Show() {
        Background {
            BackHandler {
                navController.navigate(HOME.destination) {
                    popUpTo(HOME.destination) { inclusive = true }
                }
            }
            CenteredPaddedColumn (padding = 30) {
                SelectionContainer {
                    Text(
                        textAlign = TextAlign.Center,
                        color = Color.Red,
                        text = errorText ?: "Unknown Error"
                    )
                }
            }
        }
    }
}
