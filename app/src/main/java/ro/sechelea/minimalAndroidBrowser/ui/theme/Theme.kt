package ro.sechelea.minimalAndroidBrowser.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class MinimalAndroidBrowserTheme {
    private val dynamicColor: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    @Composable
    fun Show(
        darkTheme: Boolean = isSystemInDarkTheme(),
        content: @Composable () -> Unit
    ) {
        MaterialTheme(
            colorScheme = getColorScheme(darkTheme),
            typography = Typography(),
            content = content
        )
    }

    @Composable
    private fun getColorScheme(
        darkTheme: Boolean
    ): ColorScheme =
        if (dynamicColor && darkTheme) {
            dynamicDarkColorScheme(LocalContext.current)
        } else if (dynamicColor) {
            dynamicLightColorScheme(LocalContext.current)
        } else if (darkTheme) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
}
