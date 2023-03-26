package dadm.aperher.QuotationShake.data.newquotation

import dadm.aperher.QuotationShake.model.Quotation

interface NewQuotationManager {
    suspend fun getNewQuotation(): Result<Quotation>
}