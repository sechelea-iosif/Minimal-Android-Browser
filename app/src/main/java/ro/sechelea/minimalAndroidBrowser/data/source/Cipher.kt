package ro.sechelea.minimalAndroidBrowser.data.source

import java.nio.charset.Charset
import java.security.MessageDigest

class Cipher(
    private val algorithm: String = SHA_256,
    private val charset: Charset = Charsets.UTF_8
) {
    companion object {
        private const val SHA_256 = "SHA-256"
    }

    fun encrypt(string: String) : String {
        return MessageDigest.getInstance(algorithm)
            .digest(string.toByteArray(charset))
            .toString(charset)
    }
}