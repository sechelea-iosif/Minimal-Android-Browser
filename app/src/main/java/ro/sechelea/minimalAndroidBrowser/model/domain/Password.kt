package ro.sechelea.minimalAndroidBrowser.model.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(
    @PrimaryKey
    val encrypted: String
)
