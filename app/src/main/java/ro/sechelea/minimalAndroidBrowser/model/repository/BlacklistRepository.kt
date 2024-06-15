package ro.sechelea.minimalAndroidBrowser.model.repository

import kotlinx.coroutines.flow.Flow
import ro.sechelea.minimalAndroidBrowser.model.dao.WebsiteUrlDao
import ro.sechelea.minimalAndroidBrowser.model.domain.WebsiteUrl

class BlacklistRepository(
    private val websiteUrlDao: WebsiteUrlDao
) {
    fun getAll(): Flow<List<WebsiteUrl>> = websiteUrlDao.getAll()

    suspend fun create(websiteUrl: WebsiteUrl) = websiteUrlDao.insert(websiteUrl)

    suspend fun remove(websiteUrl: WebsiteUrl) = websiteUrlDao.delete(websiteUrl)
}
