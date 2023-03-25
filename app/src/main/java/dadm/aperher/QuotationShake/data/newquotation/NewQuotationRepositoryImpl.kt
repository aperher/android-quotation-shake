package dadm.aperher.QuotationShake.data.newquotation

import dadm.aperher.QuotationShake.data.newquotation.model.toDomain
import dadm.aperher.QuotationShake.data.utils.NoInternetException
import dadm.aperher.QuotationShake.model.Quotation
import javax.inject.Inject


class NewQuotationRepositoryImpl @Inject constructor(private val datasource : NewQuotationDataSource, private val connectivity : ConnectivityChecker) : NewQuotationRepository {
    override suspend fun getNewQuotation(): Result<Quotation> {
        if(connectivity.isConnectionAvailable()) {
            return datasource.getQuotation(arrayOf("en", "ru", "xx").random()).toDomain()
        } else {
            return Result.failure(NoInternetException())
        }
    }
}