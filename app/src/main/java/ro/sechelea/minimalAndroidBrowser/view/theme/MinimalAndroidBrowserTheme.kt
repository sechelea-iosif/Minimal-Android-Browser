package ro.sechelea.minimalAndroidBrowser.view.theme

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
    @Composable
    fun Show(content: @Composable () -> Unit) {
        MaterialTheme(colorScheme = getColorScheme(), typography = Typography(), content = content)
    }

    @Composable
    private fun getColorScheme(): ColorScheme {
        val darkColor = isSystemInDarkTheme()
        val dynamicColor: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        return if (dynamicColor && darkColor) { dynamicDarkColorScheme(LocalContext.current)
        } else if (dynamicColor) {              dynamicLightColorScheme(LocalContext.current)
        } else if (darkColor) {                 darkColorScheme()
        } else {                                lightColorScheme()
        }
    }
}
