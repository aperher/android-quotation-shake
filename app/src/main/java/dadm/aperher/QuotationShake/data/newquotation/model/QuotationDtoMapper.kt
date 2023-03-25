package dadm.aperher.QuotationShake.data.newquotation.model

import dadm.aperher.QuotationShake.model.Quotation
import okio.IOException
import retrofit2.Response

fun QuotationDto.toDomain() =
    Quotation(id = quoteLink, text = quoteText, author = quoteAuthor)

fun Response<QuotationDto>.toDomain() =
    if (isSuccessful) Result.success((body() as QuotationDto).toDomain())
    else Result.failure(IOException())
