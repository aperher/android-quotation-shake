package dadm.aperher.QuotationShake.data.newquotation

import model.Quotation

interface NewQuotationRepository {
    suspend fun getNewQuotation() : Result<Quotation>
}