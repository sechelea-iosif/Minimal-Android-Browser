package ro.sechelea.minimalAndroidBrowser.base

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class UrlExtensionFunctionsTest(
    private val given: String,
    private val expected: String?
) {

    @Test
    fun testStripUrl() {
        assert(expected == given.stripUrlBeforeSecondLevelDomain())
        assert(expected?.stripUrlBeforeSecondLevelDomain() == given.stripUrlBeforeSecondLevelDomain())
        assert(expected == given.stripUrlBeforeSecondLevelDomain()?.stripUrlBeforeSecondLevelDomain())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun urlStrippedMappings(): Collection<Array<Any?>> = listOf(
            arrayOf("", null),
            arrayOf("example.com", "example.com/"),
            arrayOf("https://example.com", "example.com/"),
            arrayOf("https://www.example.com", "example.com/"),
            arrayOf("https://www.m.example.com", "example.com/"),
            arrayOf("https://en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withgiven=parameter"),
            arrayOf("http://en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withgiven=parameter"),
            arrayOf("en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withgiven=parameter"),
            arrayOf("https://www.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withgiven=parameter"),
            arrayOf("example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withgiven=parameter"),
            arrayOf("https://www.en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withgiven=parameter"),
            arrayOf("https://www.m.en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withgiven=parameter"),
            arrayOf("notIncluded://inResult.stillNot.included.STARTING_HERE_INCLUDED.inResult/andAnything/after#theFirst\\slash",
                "starting_here_included.inresult/andanything/after#thefirst\\slash"),
            arrayOf(" example.com/withLeadingAndEndingWhitespaces   ",
                "example.com/withleadingandendingwhitespaces"),
            arrayOf("HTTP://example.ORG/WoBbLyCase",
                "example.org/wobblycase"),
        )
    }
}