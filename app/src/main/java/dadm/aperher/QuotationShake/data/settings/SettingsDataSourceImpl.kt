package dadm.aperher.QuotationShake.data.settings

import android.content.SharedPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(private val sharedPreferences : SharedPreferences) : SettingsDataSource {
    object SharedPreferencesKeys  {
        const val USERNAME = "username"
        const val LANGUAGE = "language"
    }
    override fun getUsername(): Flow<String> {
        sharedPreferences.shar
    }

    private fun getUsernamePreference() = sharedPreferences.getString(SharedPreferencesKeys.USERNAME, "") ?: ""
}