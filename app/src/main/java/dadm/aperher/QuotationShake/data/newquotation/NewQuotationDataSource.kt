package dadm.aperher.QuotationShake.data.newquotation

import dadm.aperher.QuotationShake.data.newquotation.model.QuotationDto
import org.intellij.lang.annotations.Language
import retrofit2.Response

interface NewQuotationDataSource {
    suspend fun getQuotation(language: String): Response<QuotationDto>
}