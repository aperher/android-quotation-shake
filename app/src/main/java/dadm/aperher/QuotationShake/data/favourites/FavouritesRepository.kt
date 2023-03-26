package dadm.aperher.QuotationShake.data.favourites

import dadm.aperher.QuotationShake.model.Quotation
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {
    suspend fun addQuote(quote: Quotation)

    suspend fun deleteQuote(quote: Quotation)

    fun getAllQuotes(): Flow<List<Quotation>>

    fun getQuote(id: String): Flow<Quotation?>

    suspend fun deleteAllQuotes()
}