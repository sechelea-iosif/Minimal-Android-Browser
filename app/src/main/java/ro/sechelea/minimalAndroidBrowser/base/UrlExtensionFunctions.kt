package ro.sechelea.minimalAndroidBrowser.base

fun String.stripUrlBeforeSecondLevelDomain(): String? {
    val regex = "[^.]*\\.[^.]*/.*".toRegex()
    val match = regex.find(this)?.value
    return match?.trim()
}

