package ro.sechelea.minimalAndroidBrowser.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ro.sechelea.minimalAndroidBrowser.data.dao.PasswordDao
import ro.sechelea.minimalAndroidBrowser.data.domain.Password

class PasswordRepository(
    private val passwordDao: PasswordDao
) {
    fun exists(): Flow<Boolean> = passwordDao.getFirst().map { it != null }

    fun verify(password: Password): Flow<Boolean> = passwordDao.getFirst().map { it == password }

    suspend fun clearPasswords() = passwordDao.deleteAll()

    suspend fun save(password: Password) = passwordDao.insert(password)
}