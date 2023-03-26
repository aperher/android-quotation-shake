package dadm.aperher.QuotationShake.data.newquotation

import dadm.aperher.QuotationShake.model.Quotation

interface NewQuotationRepository {
    suspend fun getNewQuotation(language: String): Result<Quotation>
}