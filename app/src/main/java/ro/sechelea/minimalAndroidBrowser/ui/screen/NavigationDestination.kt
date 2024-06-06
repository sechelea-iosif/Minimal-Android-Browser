package ro.sechelea.minimalAndroidBrowser.ui.screen

enum class NavigationDestination(val destination: String) {
    HOME("home"),
    ERROR("error"),
    WEBVIEW("webview"),
    SETTINGS_PASSWORD("settings_password"),
    SETTINGS("settings")
}