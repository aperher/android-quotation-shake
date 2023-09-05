package upv.dadm.quotationshake.data.settings

import dadm.aperher.QuotationShake.data.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSettingsRepository: SettingsRepository {
    override fun getUsername(): Flow<String> {
        return flow { emit("testUser") }
    }

    override fun getLanguage(): Flow<String> {
        return flow { emit("en") }
    }
}