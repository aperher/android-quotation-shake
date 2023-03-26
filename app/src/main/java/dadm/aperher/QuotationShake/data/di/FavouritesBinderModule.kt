package dadm.aperher.QuotationShake.data.di

import dadm.aperher.QuotationShake.data.favourites.FavouritesDataSource
import dadm.aperher.QuotationShake.data.favourites.FavouritesDataSourceImpl
import dadm.aperher.QuotationShake.data.favourites.FavouritesRepository
import dadm.aperher.QuotationShake.data.favourites.FavouritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FavouritesBinderModule {
    @Binds
    abstract fun bindFavouritesRepository(repository: FavouritesRepositoryImpl): FavouritesRepository

    @Binds
    abstract fun bindFavouritesDataSource(datasource: FavouritesDataSourceImpl): FavouritesDataSource
}