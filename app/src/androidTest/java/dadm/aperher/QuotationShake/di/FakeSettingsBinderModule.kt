package dadm.aperher.QuotationShake.di

import dadm.aperher.QuotationShake.data.di.SettingsBinderModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dadm.aperher.QuotationShake.data.settings.FakeSettingsRepository
import dadm.aperher.QuotationShake.data.settings.SettingsRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [SettingsBinderModule::class]
)
abstract class FakeSettingsBinderModule {
    @Binds
    @Singleton
    abstract fun bindSettingsRepository(
        repositoryImpl: FakeSettingsRepository
    ): SettingsRepository
}