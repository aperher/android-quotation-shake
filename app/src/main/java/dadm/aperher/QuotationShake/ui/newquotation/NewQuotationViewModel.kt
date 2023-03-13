package dadm.aperher.QuotationShake.ui.newquotation

import androidx.lifecycle.*
import dadm.aperher.QuotationShake.data.newquotation.NewQuotationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import model.Quotation
import javax.inject.Inject

@HiltViewModel
class NewQuotationViewModel @Inject constructor( private val repository: NewQuotationRepository) : ViewModel() {
    private val _username = MutableLiveData<String>(getUserName())
    val username : LiveData<String>
        get() = _username

    private val _quotation = MutableLiveData<Quotation>()
    val quotation : LiveData<Quotation>
        get() = _quotation

    private val _isLoadingData = MutableLiveData<Boolean>(false)
    val isLoadingData : LiveData<Boolean>
        get() = _isLoadingData

    val isGreetingsVisible = quotation.map { it.id.isEmpty() }

    private val _isFavVisible = MutableLiveData<Boolean>(false)
    val isFavVisible : LiveData<Boolean>
        get() = _isFavVisible

    private val _exception = MutableLiveData<Throwable?>(null)
    val exception : LiveData<Throwable?>
        get() = _exception

    private fun getUserName() : String {
        return setOf("Alice", "Bob", "Charlie", "David", "Emma").random()
    }

    fun getNewQuotation() {
        _isLoadingData.value = true
/*
        val num = (0..99).random().toString()
        _quotation.value = Quotation(num, "Quotation $num", "Author $num")
*/
        viewModelScope.launch {
            repository.getNewQuotation().fold(
                onSuccess = { _quotation.value = it },
                onFailure = { _exception.value = it }
            )
        }

        _isLoadingData.value = false
        _isFavVisible.value = true
    }

    fun addToFavourites() {
        _isFavVisible.value = false
    }

    fun resetError() {
        _exception.value = null
    }
}