package ro.sechelea.minimalAndroidBrowser.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import ro.sechelea.minimalAndroidBrowser.ui.AndroidWebView

class WebViewScreen(
    private val webUrl: String
): UiScreen {
    @Composable
    override fun Show() {
        var displayedUrl by remember { mutableStateOf(webUrl) }
        Column(modifier = Modifier.fillMaxSize()) {
            UrlText(displayedUrl)
            AndroidWebView(displayedUrl,
                onValueChange = { displayedUrl = it })
        }
    }

    @Composable
    private fun UrlText(webUrl: String) {
        Card (
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth()
        ) {
            SelectionContainer {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = webUrl
                )
            }
        }
    }
}