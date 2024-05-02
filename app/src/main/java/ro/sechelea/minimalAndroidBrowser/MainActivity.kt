package ro.sechelea.minimalAndroidBrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ro.sechelea.minimalAndroidBrowser.ui.Background
import ro.sechelea.minimalAndroidBrowser.ui.theme.MinimalAndroidBrowserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
private fun Preview() {
    App()
}

@Composable
private fun App() {
    MinimalAndroidBrowserTheme().Show {
        Background {
            Greeting()
        }
    }
}

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    name: String = "World",
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
