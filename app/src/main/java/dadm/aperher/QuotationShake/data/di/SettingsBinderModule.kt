package dadm.aperher.QuotationShake.data.di

import dadm.aperher.QuotationShake.data.settings.SettingsDataSource
import dadm.aperher.QuotationShake.data.settings.SettingsDataSourceImpl
import dadm.aperher.QuotationShake.data.settings.SettingsRepository
import dadm.aperher.QuotationShake.data.settings.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsBinderModule {
    @Binds
    abstract fun bindSettingsDataSource(dataSource: SettingsDataSourceImpl) : SettingsDataSource
    @Binds
    abstract fun bindSettingsRepository(repository: SettingsRepositoryImpl) : SettingsRepository
}