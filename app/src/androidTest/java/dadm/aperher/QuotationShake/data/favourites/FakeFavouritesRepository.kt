package dadm.aperher.QuotationShake.data.favourites

import dadm.aperher.QuotationShake.domain.model.Quotation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeFavouritesRepository @Inject constructor(): FavouritesRepository {

    private val fakeFavouriteQuotations = mutableListOf<Quotation>()

    override fun getAllQuotes(): Flow<List<Quotation>> {
        return flow { emit(fakeFavouriteQuotations) }
    }

    override fun getQuote(id: String): Flow<Quotation?> {
        val quotation : Quotation? = fakeFavouriteQuotations.find { quotation -> quotation.id == id }

        return flow { emit(quotation) }
    }

    override suspend fun addQuote(quote: Quotation) {
        fakeFavouriteQuotations.add(quote)
    }

    override suspend fun deleteQuote(quote: Quotation) {
        fakeFavouriteQuotations.remove(quote)
    }

    override suspend fun deleteAllQuotes() {
        fakeFavouriteQuotations.clear()
    }
}