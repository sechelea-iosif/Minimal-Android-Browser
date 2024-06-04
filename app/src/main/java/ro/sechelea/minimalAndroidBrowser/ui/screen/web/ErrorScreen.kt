package ro.sechelea.minimalAndroidBrowser.ui.screen.web

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ro.sechelea.minimalAndroidBrowser.ui.Background
import ro.sechelea.minimalAndroidBrowser.ui.CenteredColumn
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen

class ErrorScreen(
    private val incomingUrl: String?
): UiScreen {
    @Composable
    override fun Show() {
        Background {
            CenteredColumn {
                SelectionContainer {
                    Text(
                        color = Color.Red,
                        text = "Invalid url: \"$incomingUrl\"",
                        modifier = Modifier.padding(50.dp),
                    )
                }
            }
        }
    }
}
