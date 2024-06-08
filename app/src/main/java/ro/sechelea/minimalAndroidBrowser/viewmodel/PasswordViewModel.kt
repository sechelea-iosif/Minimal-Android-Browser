package ro.sechelea.minimalAndroidBrowser.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import ro.sechelea.minimalAndroidBrowser.data.domain.Password
import ro.sechelea.minimalAndroidBrowser.data.repository.PasswordRepository
import ro.sechelea.minimalAndroidBrowser.data.source.AppDatabase
import ro.sechelea.minimalAndroidBrowser.data.source.Cipher


class PasswordViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val passwordRepository = PasswordRepository(
        AppDatabase.getIntance(application.applicationContext).passwordDao()
    )
    private val cipher = Cipher()

    fun passwordExists(): Flow<Boolean> =
        passwordRepository.exists()

    fun verify(password: String): Flow<Boolean> =
        passwordRepository.verify(encryptPassword(password))

    suspend fun remove(password: String) {
        if (verify(password).first()) {
            passwordRepository.clearPasswords()
        } else throw IllegalArgumentException("Password does not match")
    }

    suspend fun update(password: String) {
        passwordRepository.clearPasswords()
        passwordRepository.save(encryptPassword(password))
    }

    private fun encryptPassword(password: String) = Password(cipher.encrypt(password))
}