package ro.sechelea.minimalAndroidBrowser.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class WebsiteUrl(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val url: String,
    val created: LocalDateTime
) {
    companion object {
        fun createDummy(url: String = "") = WebsiteUrl(0, url, LocalDateTime.now())
    }
}
