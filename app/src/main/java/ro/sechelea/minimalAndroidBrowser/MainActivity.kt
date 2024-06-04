package ro.sechelea.minimalAndroidBrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ro.sechelea.minimalAndroidBrowser.ui.Background
import ro.sechelea.minimalAndroidBrowser.ui.screen.main.MainScreen
import ro.sechelea.minimalAndroidBrowser.ui.theme.MinimalAndroidBrowserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(intent.data?.toString())
        }
    }
}

@Preview
@Composable
private fun Preview() {
    App("Preview")
}

@Composable
private fun App(intentData: String?) {
    MinimalAndroidBrowserTheme().Show {
        Background {
            MainScreen(intentData).Show()
        }
    }
}
