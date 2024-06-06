package ro.sechelea.minimalAndroidBrowser.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ro.sechelea.minimalAndroidBrowser.data.dao.PasswordDao
import ro.sechelea.minimalAndroidBrowser.data.domain.Password

@Database(version = 1, entities = [
    Password::class
])
abstract class AppDatabase : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao

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