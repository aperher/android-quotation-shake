package upv.dadm.quotationshake.data.newquotation

import dadm.aperher.QuotationShake.data.newquotation.NewQuotationManager
import dadm.aperher.QuotationShake.domain.model.Quotation

class FakeNewQuotationManager : NewQuotationManager {

    private var quotation: Quotation? = null

    override suspend fun getNewQuotation(): Result<Quotation> {
        return if (quotation != null)
            Result.success(quotation!!)
        else
            Result.failure(Throwable())
    }

    fun addQuotation(quotation: Quotation) {
        this.quotation = quotation
    }
}