package dadm.aperher.QuotationShake.di

import dadm.aperher.QuotationShake.data.di.NewQuotationBinderModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dadm.aperher.QuotationShake.data.newquotation.FakeNewQuotationManager
import dadm.aperher.QuotationShake.data.newquotation.NewQuotationManager
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NewQuotationBinderModule::class]
)
abstract class FakeNewQuotationBinderModule {

    @Binds
    @Singleton
    abstract fun bindNewQuotationManager(
        newQuotationManagerImpl: FakeNewQuotationManager
    ): NewQuotationManager
}