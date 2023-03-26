package dadm.aperher.QuotationShake.data.favourites

import dadm.aperher.QuotationShake.data.favourites.model.QuotationDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavouritesDataSourceImpl @Inject constructor(private val dao: FavouritesDao) :
    FavouritesDataSource {
    override suspend fun addQuote(quote: QuotationDto) {
        dao.addQuote(quote)
    }

    override suspend fun deleteQuote(quote: QuotationDto) {
        dao.deleteQuote(quote)
    }

    override fun getAllQuotes(): Flow<List<QuotationDto>> = dao.getAllQuotes()

    override fun getQuote(id: String): Flow<QuotationDto> = dao.getQuote(id)

    override fun deleteAllQuotes() {
        dao.deleteAllQuotes()
    }
}