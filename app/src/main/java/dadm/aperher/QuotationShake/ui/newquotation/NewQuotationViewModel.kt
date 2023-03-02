package dadm.aperher.QuotationShake.ui.newquotation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import model.Quotation

class NewQuotationViewModel : ViewModel() {
    private val _username = MutableLiveData<String>(getUserName())
    val username : LiveData<String>
        get() = _username

    private val _quotation = MutableLiveData<Quotation>()
    val quotation : LiveData<Quotation>
        get() = _quotation

    private val _isLoadingData = MutableLiveData<Boolean>(false)
    val isLoadingData : LiveData<Boolean>
        get() = _isLoadingData

    val isGreetingsVisible = Transformations.map(quotation) { it == null }

    private val _isFavVisible = MutableLiveData<Boolean>(false)
    val isFavVisible : LiveData<Boolean>
        get() = _isFavVisible

    private fun getUserName() : String {
        return setOf("Alice", "Bob", "Charlie", "David", "Emma").random()
    }

    fun getNewQuotation() {
        _isLoadingData.value = true

        val num = (0..99).random().toString()
        _quotation.value = Quotation(num, "Quotation $num", "Author $num")

        _isLoadingData.value = false
        _isFavVisible.value = true
    }

    fun addToFavourites() {
        _isFavVisible.value = false
    }
}