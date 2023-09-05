package dadm.aperher.QuotationShake.ui.newquotation

import androidx.lifecycle.*
import dadm.aperher.QuotationShake.data.favourites.FavouritesRepository
import dadm.aperher.QuotationShake.data.newquotation.NewQuotationManager
import dadm.aperher.QuotationShake.data.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import dadm.aperher.QuotationShake.domain.model.Quotation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    settingsRepository: SettingsRepository,
    private val newQuotationManager: NewQuotationManager,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {
    val username: LiveData<String> = settingsRepository.getUsername().asLiveData()

    private val _quotation = MutableLiveData<Quotation>()
    val quotation: LiveData<Quotation>
        get() = _quotation

    private val _isLoadingData = MutableLiveData(false)
    val isLoadingData: LiveData<Boolean>
        get() = _isLoadingData

    val isGreetingsVisible = quotation.map { it.id.isEmpty() }

    val isFavVisible: LiveData<Boolean> = quotation.switchMap { newQuotation ->
        favouritesRepository.getQuote(newQuotation.id).asLiveData()
    }.map { favourite ->
        favourite == null
    }

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    fun getNewQuotation() {
        _isLoadingData.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val quotation = newQuotationManager.getNewQuotation()

            withContext(Dispatchers.Main) {
                if (quotation.isSuccess) {
                    _quotation.value = quotation.getOrNull()
                } else {
                    _error.value = quotation.exceptionOrNull()
                }
            }
        }

        _isLoadingData.value = false
    }

    fun addToFavourite() {
        viewModelScope.launch {
            favouritesRepository.addQuote(quotation.value!!)
        }
    }

    fun resetError() {
        _error.value = null
    }
}