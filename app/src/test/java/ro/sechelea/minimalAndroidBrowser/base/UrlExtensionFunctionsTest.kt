package ro.sechelea.minimalAndroidBrowser.base

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class UrlExtensionFunctionsTest(
    private val given: String,
    private val expected: String
) {

    @Test
    fun testStripUrl() {
        assert(expected == given.stripUrlBeforeSecondLevelDomain())
        assert(expected.stripUrlBeforeSecondLevelDomain() == given.stripUrlBeforeSecondLevelDomain())
        assert(expected == given.stripUrlBeforeSecondLevelDomain()!!.stripUrlBeforeSecondLevelDomain())
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun urlStrippedMappings(): Collection<Array<Any>> = listOf(
            arrayOf("https://en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withGiven=parameter"),
            arrayOf("http://en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withGiven=parameter"),
            arrayOf("en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withGiven=parameter"),
            arrayOf("https://www.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withGiven=parameter"),
            arrayOf("example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withGiven=parameter"),
            arrayOf("https://www.en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withGiven=parameter"),
            arrayOf("https://www.m.en.example.org/path/to/resource?withGiven=parameter",
                "example.org/path/to/resource?withGiven=parameter"),
            arrayOf("notIncluded://inResult.stillNot.included.STARTING_HERE_INCLUDED.inResult/andAnything/after#theFirst\\slash",
                "STARTING_HERE_INCLUDED.inResult/andAnything/after#theFirst\\slash"),
            arrayOf(" example.com/withLeadingAndEndingWhitespaces   ",
                "example.com/withLeadingAndEndingWhitespaces")
        )
    }
}