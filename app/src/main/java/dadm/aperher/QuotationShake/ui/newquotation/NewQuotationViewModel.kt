package dadm.aperher.QuotationShake.ui.newquotation

import androidx.lifecycle.*
import dadm.aperher.QuotationShake.data.favourites.FavouritesRepository
import dadm.aperher.QuotationShake.data.newquotation.NewQuotationManager
import dadm.aperher.QuotationShake.data.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import dadm.aperher.QuotationShake.model.Quotation
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor(
    private val newQuotationRepository: NewQuotationManager,
    private val settingsRepository: SettingsRepository,
    private val favouritesRepository: FavouritesRepository
) : ViewModel() {
    val username: LiveData<String> = settingsRepository.getUsername().asLiveData()

    private val _quotation = MutableLiveData<Quotation>()
    val quotation: LiveData<Quotation>
        get() = _quotation

    private val _isLoadingData = MutableLiveData<Boolean>(false)
    val isLoadingData: LiveData<Boolean>
        get() = _isLoadingData

    val isGreetingsVisible = quotation.map { it.id.isEmpty() }

    private val _isFavVisible = MutableLiveData<Boolean>(false)
    val isFavVisible: LiveData<Boolean>
        get() = _isFavVisible

    private val _exception = MutableLiveData<Throwable?>(null)
    val exception: LiveData<Throwable?>
        get() = _exception

    fun getNewQuotation() {
        _isLoadingData.value = true

        viewModelScope.launch {
            newQuotationRepository.getNewQuotation().fold(
                onSuccess = { _quotation.value = it },
                onFailure = { _exception.value = it }
            )
        }

        _isLoadingData.value = false
        _isFavVisible.value = true
    }

    fun addToFavourites() {
        viewModelScope.launch {
            favouritesRepository.addQuote(quotation.value!!)
            _isFavVisible.value = false
        }
    }

    fun resetError() {
        _exception.value = null
    }
}