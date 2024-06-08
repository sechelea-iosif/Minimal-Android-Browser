package ro.sechelea.minimalAndroidBrowser.data.repository

import kotlinx.coroutines.flow.Flow
import ro.sechelea.minimalAndroidBrowser.data.dao.WebsiteUrlDao
import ro.sechelea.minimalAndroidBrowser.data.domain.WebsiteUrl

class BlacklistRepository(
    private val websiteUrlDao: WebsiteUrlDao
) {
    fun getAll(): Flow<List<WebsiteUrl>> = websiteUrlDao.getAll()

    suspend fun create(websiteUrl: WebsiteUrl) = websiteUrlDao.insert(websiteUrl)

    suspend fun remove(websiteUrl: WebsiteUrl) = websiteUrlDao.delete(websiteUrl)
}
