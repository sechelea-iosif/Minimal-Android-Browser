package ro.sechelea.minimalAndroidBrowser.ui.screen.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import ro.sechelea.minimalAndroidBrowser.ui.Background
import ro.sechelea.minimalAndroidBrowser.ui.CenteredColumn
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen
import ro.sechelea.minimalAndroidBrowser.ui.screen.main.Navigation

class SettingsPasswordScreen(
    private val navController: NavHostController
) : UiScreen {

    @Composable
    override fun Show() {
        Background {
            CenteredColumn {
                Row {
                    PasswordForm()
                }
            }
        }
    }

    @Composable
    private fun PasswordForm() {
        var givenPassword by remember { mutableStateOf("") }
        var passwordMatches by remember { mutableStateOf(true) }

        OutlinedTextField(
            value = givenPassword,
            onValueChange = {givenPassword = it},
            label = { Text("Password (1234)") },
            isError = !passwordMatches
        )

        IconButton(onClick = {
            passwordMatches = checkPassword(givenPassword)
            if (passwordMatches) {
                navController.navigate(Navigation.NavigationDestination.SETTINGS.destination)
            } else {
                givenPassword = ""
            }
        }) {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = "Check"
            )
        }
    }

    private fun checkPassword(givenPassword: String): Boolean {
        return givenPassword == "1234"
    }
}
