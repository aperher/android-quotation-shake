package dadm.aperher.QuotationShake.data.newquotation

import dadm.aperher.QuotationShake.domain.model.Quotation
import upv.dadm.quotationshake.Constants
import javax.inject.Inject

class FakeNewQuotationManager @Inject constructor(): NewQuotationManager {

    private var quotation: Quotation = Quotation(id = Constants.quotationId, text = Constants.quotationText, author = Constants.quotationAuthor)

    override suspend fun getNewQuotation(): Result<Quotation> {
        return Result.success(quotation)
    }

    fun addQuotation(quotation: Quotation) {
        this.quotation = quotation
    }
}