package dadm.aperher.QuotationShake.data.settings

import kotlinx.coroutines.flow.Flow

interface SettingsDataSource {
    fun getUsername() : Flow<String>
}