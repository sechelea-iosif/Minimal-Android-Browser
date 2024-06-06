package ro.sechelea.minimalAndroidBrowser.ui.screen.main

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ro.sechelea.minimalAndroidBrowser.ui.screen.NavigationDestination.SETTINGS_PASSWORD
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen

class MainScreen(
    private val incomingUrl: String?
) : UiScreen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Show() {
        val navController = rememberNavController()
        var titleText by remember { mutableStateOf(incomingUrl ?: "") }
        Scaffold(topBar = {
            TopAppBar(
                title = { TitleText(titleText) },
                actions = { SettingsIconButton(navController) }
            )
        }) { innerPadding ->
            Navigation(
                navController,
                incomingUrl,
                { titleText = it },
                innerPadding
            ).Show()
        }
    }

    @Composable
    private fun TitleText(title: String) {
        Card(
            shape = RectangleShape,
            modifier = Modifier.fillMaxWidth()
        ) {
            SelectionContainer {
                val sideScroll = rememberScrollState(0)
                Text(
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                        .horizontalScroll(sideScroll),
                    maxLines = 1,
                    text = title
                )
            }
        }
    }

    @Composable
    private fun SettingsIconButton(navController: NavHostController) {
        IconButton(onClick = { navController.navigate(SETTINGS_PASSWORD.destination) }) {
            Icon(imageVector = Icons.Rounded.Settings, contentDescription = "Settings")
        }
    }
}
