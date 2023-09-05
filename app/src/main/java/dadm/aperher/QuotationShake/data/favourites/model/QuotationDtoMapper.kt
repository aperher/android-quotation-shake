package dadm.aperher.QuotationShake.data.favourites.model

import dadm.aperher.QuotationShake.domain.model.Quotation

fun QuotationDto.toDomain() = Quotation(id = id, text = text, author = author)

fun Quotation.toDto() = QuotationDto(id = id, text = text, author = author)