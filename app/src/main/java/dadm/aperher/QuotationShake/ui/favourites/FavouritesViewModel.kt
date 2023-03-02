package dadm.aperher.QuotationShake.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import model.Quotation

class FavouritesViewModel : ViewModel() {
    private val _quotationList = MutableLiveData<List<Quotation>>(getFavouriteQuotation())
    val quotationList : LiveData<List<Quotation>>
        get() = _quotationList

    val isDeleteItemVisible : LiveData<Boolean> = Transformations.map(quotationList) { it.isNotEmpty() }

    private fun getFavouriteQuotation() : List<Quotation> {
        return(1..20).map {
            val id = (0..99).random().toString()
            Quotation(id, "Quotation $id", "Author $id")
        }
    }

    fun deleteAllQuotations() {
        _quotationList.value = emptyList()
    }
}