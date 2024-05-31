package ro.sechelea.minimalAndroidBrowser.base

fun String.stripUrlBeforeSecondLevelDomain(): String? {
    val url = this.lowercase().trim() + "/"
    val regex = "[^./]*\\.[^.]*/.*".toRegex()
    val match = regex.find(url)?.value
    val trimmed = match?.dropLast(1)
    return when {
        trimmed == null -> null
        !trimmed.contains("/") -> "$trimmed/"
        else -> trimmed
    }
}

