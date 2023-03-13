package dadm.aperher.QuotationShake.data.newquotation

import dadm.aperher.QuotationShake.data.newquotation.model.QuotationDto
import model.Quotation
import retrofit2.Response
import javax.inject.Inject

class NewQuotationDataSourceImpl @Inject constructor() : NewQuotationDataSource {
    override suspend fun getQuotation(): Response<QuotationDto> {
        if((0..9).random() != 9) {
            val id = (0..99).random().toString()
            return Response.success(QuotationDto(id,"Quotation $id"," Author $id"))
        } else
            return Result.failure(Exception("Error getting new quotation"))
    }
}