package dadm.aperher.QuotationShake.data.newquotation

import dadm.aperher.QuotationShake.data.newquotation.model.QuotationDto
import model.Quotation
import retrofit2.Response

interface NewQuotationDataSource {
    suspend fun getQuotation(): Response<QuotationDto>
}