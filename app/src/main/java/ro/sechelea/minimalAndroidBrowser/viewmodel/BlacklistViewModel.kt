package ro.sechelea.minimalAndroidBrowser.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import ro.sechelea.minimalAndroidBrowser.data.domain.WebsiteUrl
import ro.sechelea.minimalAndroidBrowser.data.repository.BlacklistRepository
import ro.sechelea.minimalAndroidBrowser.data.source.AppDatabase
import java.time.LocalDateTime

class BlacklistViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val blacklistRepository = BlacklistRepository(
        AppDatabase.getIntance(application.applicationContext).websiteUrlDao()
    )

    fun getAll(): Flow<List<WebsiteUrl>> = blacklistRepository.getAll()

    suspend fun add(url: String) {
        blacklistRepository.create(WebsiteUrl(0, url, LocalDateTime.now()))
    }

    suspend fun remove(websiteUrl: WebsiteUrl) {
        blacklistRepository.remove(websiteUrl)
    }
}