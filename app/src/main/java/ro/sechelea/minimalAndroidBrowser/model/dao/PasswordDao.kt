package ro.sechelea.minimalAndroidBrowser.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ro.sechelea.minimalAndroidBrowser.model.domain.Password

@Dao
interface PasswordDao {
    @Query("select * from password limit 1")
    fun getFirst(): Flow<Password?>

    @Insert
    suspend fun insert(password: Password)

    @Query("delete from password")
    suspend fun deleteAll()
}