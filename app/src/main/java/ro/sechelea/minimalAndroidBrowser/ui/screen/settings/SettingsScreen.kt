package ro.sechelea.minimalAndroidBrowser.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import ro.sechelea.minimalAndroidBrowser.data.domain.WebsiteUrl
import ro.sechelea.minimalAndroidBrowser.ui.Background
import ro.sechelea.minimalAndroidBrowser.ui.CenteredPaddedColumn
import ro.sechelea.minimalAndroidBrowser.ui.CenteredSpacedRow
import ro.sechelea.minimalAndroidBrowser.ui.PasswordFieldWithButton
import ro.sechelea.minimalAndroidBrowser.ui.screen.NavigationDestination.CHECK_PASSWORD
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen
import ro.sechelea.minimalAndroidBrowser.viewmodel.BlacklistViewModel
import ro.sechelea.minimalAndroidBrowser.viewmodel.PasswordViewModel

class SettingsScreen(
    private val navController: NavHostController,
    private val passwordChecked: Boolean
) : UiScreen {
    @Composable
    override fun Show() {
        Background {
            CenteredPaddedColumn {
                PasswordForm()
                BlackListedWebsites()
            }
        }
    }

    @Composable
    private fun PasswordForm() {
        val passwordViewModel: PasswordViewModel = viewModel()
        val isPasswordSet: Boolean by passwordViewModel.passwordExists().collectAsState(initial = false)

        if (isPasswordSet && !passwordChecked) navController.navigate(CHECK_PASSWORD.destination)

        Card(modifier = Modifier.padding(10.dp)) { Column {
            Text(text = if (isPasswordSet) "Password Set. Change your Password?" else "Set new Password")

            val coroutineScope = rememberCoroutineScope()
            PasswordFieldWithButton(function = { coroutineScope.launch {
                passwordViewModel.update(it)
            } }, buttonText = "Update")

            Text(text = "Remove Password (confirm by introducing old Password)")

            var wrongPassword by remember { mutableStateOf("") }
            PasswordFieldWithButton(function = { coroutineScope.launch { wrongPassword =
                try { passwordViewModel.remove(it); ""
                } catch (_ : IllegalArgumentException) { "Wrong Password" }
            } }, buttonText = "Remove", errorText = wrongPassword)
        } }
    }

    @Composable
    private fun BlackListedWebsites() {
        Text(text = "Blacklisted Websites will not be loaded (you may use regex)")

        val blacklistViewModel: BlacklistViewModel = viewModel()
        val coroutineScope = rememberCoroutineScope()

        var newWebsite by remember { mutableStateOf("") }
        CenteredSpacedRow {
            TextField(value = newWebsite, onValueChange =  { newWebsite = it })

            Button(onClick = { coroutineScope.launch {
                blacklistViewModel.add(newWebsite)
                newWebsite = ""
            } } ) {
                Text(text = "Add")
            }
        }

        val blacklist : List<WebsiteUrl> by blacklistViewModel.getAll()
            .collectAsState(initial = listOf(WebsiteUrl.createDummy("Loading...")))
        blacklist.forEach {
            CenteredSpacedRow {
                Text(text = it.url)

                Button(onClick = { coroutineScope.launch {
                    blacklistViewModel.remove(it)
                } }) {
                    Icon(contentDescription = "Remove", imageVector = Icons.Default.Delete)
                }
            }
        }
    }
}
