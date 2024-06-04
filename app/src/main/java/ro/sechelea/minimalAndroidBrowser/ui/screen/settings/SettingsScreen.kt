package ro.sechelea.minimalAndroidBrowser.ui.screen.settings

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ro.sechelea.minimalAndroidBrowser.ui.Background
import ro.sechelea.minimalAndroidBrowser.ui.CenteredColumn
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen

class SettingsScreen : UiScreen {
    @Composable
    override fun Show() {
        Background {
            CenteredColumn {
                Text(text = "Settings under construction")
            }
        }
    }
}
