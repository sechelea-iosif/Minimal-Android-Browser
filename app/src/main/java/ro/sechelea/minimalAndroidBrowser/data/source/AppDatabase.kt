package ro.sechelea.minimalAndroidBrowser.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ro.sechelea.minimalAndroidBrowser.data.dao.PasswordDao
import ro.sechelea.minimalAndroidBrowser.data.dao.WebsiteUrlDao
import ro.sechelea.minimalAndroidBrowser.data.domain.Password
import ro.sechelea.minimalAndroidBrowser.data.domain.WebsiteUrl

@Database(version = 2, entities = [
    Password::class,
    WebsiteUrl::class
])
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao
    abstract fun websiteUrlDao(): WebsiteUrlDao

    companion object{
        private const val NAME = "minimalAndroidBrowser.db"
        private var INSTANCE: AppDatabase? = null

        fun getIntance(context: Context): AppDatabase = synchronized(this) {
            return INSTANCE ?: buildDatabase(context).also{ INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, AppDatabase::class.java, NAME
        ).build()
    }
}