package dadm.aperher.QuotationShake.data.settings

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeSettingsRepository @Inject constructor(): SettingsRepository {
    override fun getUsername(): Flow<String> {
        return flow { emit("testUser") }
    }

    override fun getLanguage(): Flow<String> {
        return flow { emit("en") }
    }
}