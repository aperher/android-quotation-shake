package dadm.aperher.QuotationShake.ui.favourites

import androidx.lifecycle.*
import dadm.aperher.QuotationShake.data.favourites.FavouritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dadm.aperher.QuotationShake.domain.model.Quotation
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel@Inject constructor(private val favouritesRepository: FavouritesRepository) : ViewModel() {
    val quotationList : LiveData<List<Quotation>> = favouritesRepository.getAllQuotes().asLiveData()

    val isDeleteItemVisible : LiveData<Boolean> = quotationList.map { it.isNotEmpty() }

    fun deleteAllQuotations() {
        viewModelScope.launch {
            favouritesRepository.deleteAllQuotes()
        }
    }

    fun deleteQuotationAtPosition(position : Int) {
        viewModelScope.launch {
            favouritesRepository.deleteQuote(quotationList.value!![position])
        }
    }
}