package ro.sechelea.minimalAndroidBrowser.data.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(
    @PrimaryKey
    val encrypted: String
)
