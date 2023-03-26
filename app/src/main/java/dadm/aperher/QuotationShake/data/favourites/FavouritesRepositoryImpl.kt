package dadm.aperher.QuotationShake.data.favourites

import dadm.aperher.QuotationShake.data.favourites.model.toDomain
import dadm.aperher.QuotationShake.data.favourites.model.toDto
import dadm.aperher.QuotationShake.model.Quotation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavouritesRepositoryImpl @Inject constructor(private val datasource: FavouritesDataSource) :
    FavouritesRepository {
    override suspend fun addQuote(quote: Quotation) {
        datasource.addQuote(quote.toDto())
    }

    override suspend fun deleteQuote(quote: Quotation) {
        datasource.deleteQuote(quote.toDto())
    }

    override fun getAllQuotes(): Flow<List<Quotation>> {
        return datasource.getAllQuotes().map {
            it.map {
                it.toDomain()
            }
        }
    }

    override fun getQuote(id: String): Flow<Quotation?> {
        return datasource.getQuote(id).map {
            it?.toDomain()
        }
    }

    override suspend fun deleteAllQuotes() {
        datasource.deleteAllQuotes()
    }
}