package ro.sechelea.minimalAndroidBrowser.ui.screen.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ro.sechelea.minimalAndroidBrowser.ui.Background
import ro.sechelea.minimalAndroidBrowser.ui.CenteredPaddedColumn
import ro.sechelea.minimalAndroidBrowser.ui.CenteredSpacedRow
import ro.sechelea.minimalAndroidBrowser.ui.screen.NavigationDestination.Companion.SETTINGS
import ro.sechelea.minimalAndroidBrowser.ui.screen.UiScreen
import ro.sechelea.minimalAndroidBrowser.viewmodel.PasswordViewModel

class CheckPasswordScreen(
    private val navController: NavHostController
) : UiScreen {

    @Composable
    override fun Show() {
        Background {
            BackHandler {
                navController.popBackStack(SETTINGS(passwordChecked = false), inclusive = true)
            }
            CenteredPaddedColumn(padding = 30) {
                CenteredSpacedRow {
                    CheckPasswordForm()
                }
            }
        }
    }

    @Composable
    private fun CheckPasswordForm() {
        val passwordViewModel: PasswordViewModel = viewModel()
        var userInputPw by remember { mutableStateOf("") }
        var errorText by remember { mutableStateOf("") }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(0.7F),
            value = userInputPw,
            onValueChange = {userInputPw = it},
            label = { Text("Password") },
            isError = errorText.isEmpty(),
            supportingText = { Text(text = errorText) }
        )

        val coroutineScope = rememberCoroutineScope()

        Button(onClick = { coroutineScope.launch {
            if (passwordViewModel.verify(userInputPw).first()) {
                navController.popBackStack(SETTINGS(passwordChecked = false), inclusive = true)
                navController.navigate(SETTINGS(passwordChecked = true))
            } else {
                userInputPw = ""
                errorText = "Wrong Password"
            }
        } } ) { Text(text = "Check") }
    }
}
