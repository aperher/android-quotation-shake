package dadm.aperher.QuotationShake.data.settings

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val dataSource: SettingsDataSource) :
    SettingsRepository {
    override fun getUsername(): Flow<String> = dataSource.getUsername()
    override fun getLanguage(): Flow<String> = dataSource.getLanguage()

}