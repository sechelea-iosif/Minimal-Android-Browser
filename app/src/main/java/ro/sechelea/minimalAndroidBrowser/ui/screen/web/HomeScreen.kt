package ro.sechelea.minimalAndroidBrowser.ui.screen.web

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import ro.sechelea.minimalAndroidBrowser.ui.Background
import ro.sechelea.minimalAndroidBrowser.ui.CenteredPaddedColumn
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen

class HomeScreen : UiScreen {
    @Composable
    override fun Show() {
        Background {
            CenteredPaddedColumn(padding = 30) {
                SelectionContainer {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "Scan a QR code or open a link from another app to view a web page.",
                    )
                }
            }
        }
    }
}
