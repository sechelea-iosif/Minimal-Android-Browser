package ro.sechelea.minimalAndroidBrowser.view.screen

enum class NavigationDestination(val destination: String) {
    HOME("home"),
    ERROR("error"),
    WEBVIEW("webview"),
    CHECK_PASSWORD("check_password"),
    SETTINGS("settings");

    companion object {
        fun SETTINGS(passwordChecked: Boolean): String = "${SETTINGS.destination}/$passwordChecked"

        fun ERROR(text: String): String = "${ERROR.destination}?errorText=$text"
    }
}