package ro.sechelea.minimalAndroidBrowser.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun Background(
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        content()
    }
}

@Composable
fun CenteredPaddedColumn(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        content()
    }
}

@Composable
fun PasswordFieldWithButton(
    label: String = "Password",
    function: (String) -> Unit,
    buttonText: String = "Go",
    errorText: String = ""
) {
    CenteredSpacedRow {
        val password: MutableState<String> = remember { mutableStateOf("") }
        PasswordOutlinedTextField(value = password, label = label, errorText = errorText)
        CallBackButton(password, function, buttonText)
    }
}

@Composable
fun CenteredSpacedRow(
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        content()
    }
}

@Composable
private fun CallBackButton(
    password: MutableState<String>,
    function: (String) -> Unit,
    buttonText: String
) {
    Button(onClick = { function(password.value) }) {
        Text(text = buttonText)
    }
}

@Composable
private fun PasswordOutlinedTextField(
    value: MutableState<String>,
    label: String = "",
    errorText: String
) {
    val isVisible = remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value.value,
        onValueChange = { value.value = it },
        label = { Text(text = label) },
        singleLine = true,
        placeholder = { Text(text = "Password") },
        supportingText = { Text(text = errorText) },
        isError = errorText.isNotEmpty(),
        visualTransformation = if (isVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = { VisibilityToggleButton(isVisible) }
    )
}

@Composable
private fun VisibilityToggleButton(visibility: MutableState<Boolean>) {
    IconButton(onClick = { visibility.value = !visibility.value }) {
        Icon(imageVector = if (visibility.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
            contentDescription = null)
    }
}
