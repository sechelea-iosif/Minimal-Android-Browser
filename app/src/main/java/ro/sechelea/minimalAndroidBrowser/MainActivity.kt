package ro.sechelea.minimalAndroidBrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import ro.sechelea.minimalAndroidBrowser.view.Background
import ro.sechelea.minimalAndroidBrowser.view.screen.main.MainScreen
import ro.sechelea.minimalAndroidBrowser.view.theme.MinimalAndroidBrowserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(intent.data?.toString())
        }
    }

    @Composable
    private fun App(intentData: String?) {
        MinimalAndroidBrowserTheme().Show {
            Background {
                MainScreen(intentData).Show()
            }
        }
    }
}
