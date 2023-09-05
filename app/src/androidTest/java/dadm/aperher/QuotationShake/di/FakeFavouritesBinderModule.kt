package dadm.aperher.QuotationShake.di

import dadm.aperher.QuotationShake.data.di.FavouritesBinderModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dadm.aperher.QuotationShake.data.favourites.FakeFavouritesRepository
import dadm.aperher.QuotationShake.data.favourites.FavouritesRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [FavouritesBinderModule::class]
)
abstract class FakeFavouritesBinderModule {

    @Singleton
    @Binds
    abstract fun bindFavouritesRepository(
        favouritesRepositoryImpl: FakeFavouritesRepository
    ): FavouritesRepository
}