package ro.sechelea.minimalAndroidBrowser.model.source

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeConverter {
    @TypeConverter
    fun localDateTimeToString(localDateTime: LocalDateTime): String =
        localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)

    @TypeConverter
    fun stringToLocalDateTime(string: String): LocalDateTime =
        LocalDateTime.parse(string, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}
