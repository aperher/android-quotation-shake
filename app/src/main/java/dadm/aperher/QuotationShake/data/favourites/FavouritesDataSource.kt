package dadm.aperher.QuotationShake.data.favourites

import dadm.aperher.QuotationShake.data.favourites.model.QuotationDto
import kotlinx.coroutines.flow.Flow

interface FavouritesDataSource {
    suspend fun addQuote(quote: QuotationDto)

    suspend fun deleteQuote(quote: QuotationDto)

    fun getAllQuotes(): Flow<List<QuotationDto>>

    fun getQuote(id: String): Flow<QuotationDto>

    fun deleteAllQuotes()
}