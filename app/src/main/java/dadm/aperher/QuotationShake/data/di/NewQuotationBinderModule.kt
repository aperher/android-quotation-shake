package dadm.aperher.QuotationShake.data.di

import dadm.aperher.QuotationShake.data.newquotation.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class) // Con ViewModelComponent sería más correcto porque es donde se inyecta el repository, por tanto crearlo cuando el ViewModel se crea. Sin embargo, es lo mismo que inyectarlo a nivel a aplicación ya que la instancia de fragment se crea al abrir la actividad y por tanto su viewmodel
abstract class NewQuotationBinderModule {
    @Binds
    abstract fun bindNewQuotationRepository(repository: NewQuotationRepositoryImpl): NewQuotationRepository

    @Binds
    abstract fun bindNewQuotationDataSource(dataSource: NewQuotationDataSourceImpl): NewQuotationDataSource

    @Binds
    abstract fun bindNewQuotationManager(manager: NewQuotationManagerImpl): NewQuotationManager
}