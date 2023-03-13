package dadm.aperher.QuotationShake.di

import dadm.aperher.QuotationShake.data.newquotation.NewQuotationDataSource
import dadm.aperher.QuotationShake.data.newquotation.NewQuotationDataSourceImpl
import dadm.aperher.QuotationShake.data.newquotation.NewQuotationRepository
import dadm.aperher.QuotationShake.data.newquotation.NewQuotationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NewQuotationBinderModule {
    @Binds
    abstract fun bindNewQuotationRepository(repository: NewQuotationRepositoryImpl) : NewQuotationRepository
    @Binds
    abstract fun bindNewQuotationDataSource(dataSource: NewQuotationDataSourceImpl) : NewQuotationDataSource
}