package ro.sechelea.minimalAndroidBrowser.service

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import ro.sechelea.minimalAndroidBrowser.data.domain.Password
import ro.sechelea.minimalAndroidBrowser.data.repository.PasswordRepository
import ro.sechelea.minimalAndroidBrowser.data.source.AppDatabase


class PasswordManager(
    application: Application
) : AndroidViewModel(application) {
    private val passwordRepository = PasswordRepository(
        AppDatabase.getIntance(application.applicationContext).passwordDao()
    )

    fun passwordExists(): Flow<Boolean> =
        passwordRepository.exists()

    fun verify(password: String): Flow<Boolean> =
        passwordRepository.verify(encrypt(password))

    suspend fun remove(password: String) {
        if (verify(password).first()) {
            passwordRepository.clearPasswords()
        } else throw IllegalArgumentException("Password does not match")
    }

    suspend fun update(password: String) {
        if (passwordExists().first()) {
            passwordRepository.clearPasswords()
            passwordRepository.save(encrypt(password))
        }
    }

    private fun encrypt(password: String): Password {
        // todo add real encryption
        return Password("$password-encrypted")
    }
}