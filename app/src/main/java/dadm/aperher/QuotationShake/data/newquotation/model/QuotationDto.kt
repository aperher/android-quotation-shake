package dadm.aperher.QuotationShake.data.newquotation.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class QuotationDto(private val quoteText: String, private val quoteAuthor: String, private val senderName : String, private val senderLink : String, private val quoteLink : String)