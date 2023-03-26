package dadm.aperher.QuotationShake.data.favourites

import androidx.room.*
import dadm.aperher.QuotationShake.data.favourites.FavouritesContract.FavouritesQuotes.COLUMN_ID
import dadm.aperher.QuotationShake.data.favourites.FavouritesContract.FavouritesQuotes.TABLE_NAME
import dadm.aperher.QuotationShake.data.favourites.model.QuotationDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuote(quote: QuotationDto)

    @Delete
    suspend fun deleteQuote(quote: QuotationDto)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllQuotes(): Flow<List<QuotationDto>>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    fun getQuote(id: String): Flow<QuotationDto?>

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAllQuotes()
}