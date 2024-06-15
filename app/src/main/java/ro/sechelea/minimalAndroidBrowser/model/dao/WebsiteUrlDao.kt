package ro.sechelea.minimalAndroidBrowser.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ro.sechelea.minimalAndroidBrowser.model.domain.WebsiteUrl

@Dao
interface WebsiteUrlDao {
    @Query("select * from websiteurl order by created desc")
    fun getAll(): Flow<List<WebsiteUrl>>

    @Insert
    suspend fun insert(websiteUrl: WebsiteUrl)

    @Delete
    suspend fun delete(websiteUrl: WebsiteUrl)
}
