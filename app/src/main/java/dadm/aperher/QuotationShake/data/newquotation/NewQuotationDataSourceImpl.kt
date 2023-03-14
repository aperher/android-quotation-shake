package dadm.aperher.QuotationShake.data.newquotation

import dadm.aperher.QuotationShake.data.newquotation.model.QuotationDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class NewQuotationDataSourceImpl @Inject constructor(retrofit : Retrofit) : NewQuotationDataSource {
    private val retrofitQuotationService = retrofit.create(NewQuotationRetrofit::class.java)
    override suspend fun getQuotation(): Response<QuotationDto> {
        try {
            return retrofitQuotationService.getQuotation()
        } catch (e: Exception) {
            return Response.error(400, ResponseBody.create(MediaType.parse("text/plain"), e.toString()))
        }
    }

    interface NewQuotationRetrofit {
        @GET("api/1.0/?method=getQuote&format=json&lang=en")
        suspend fun getQuotation(): Response<QuotationDto>
    }
}