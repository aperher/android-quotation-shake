package dadm.aperher.QuotationShake.data.di

import android.content.Context
import androidx.room.Room
import dadm.aperher.QuotationShake.data.favourites.FavouritesContract
import dadm.aperher.QuotationShake.data.favourites.FavouritesDao
import dadm.aperher.QuotationShake.data.favourites.FavouritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FavouritesProviderModule {
    @Provides
    @Singleton
    fun provideFavouritesDatabase(@ApplicationContext context: Context): FavouritesDatabase =
        Room.databaseBuilder(
            context,
            FavouritesDatabase::class.java,
            FavouritesContract.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideFavouritesDao(database: FavouritesDatabase): FavouritesDao = database.favouritesDao()
}