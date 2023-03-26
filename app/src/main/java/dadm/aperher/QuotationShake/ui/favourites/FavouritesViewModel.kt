package dadm.aperher.QuotationShake.ui.favourites

import androidx.lifecycle.*
import dadm.aperher.QuotationShake.data.favourites.FavouritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dadm.aperher.QuotationShake.model.Quotation
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel@Inject constructor(private val favouritesRepository: FavouritesRepository) : ViewModel() {
    val quotationList : LiveData<List<Quotation>> = favouritesRepository.getAllQuotes().asLiveData()

    val isDeleteItemVisible : LiveData<Boolean> = quotationList.map { it.isNotEmpty() }

    /*private fun getFavouriteQuotation() : List<Quotation>  =
        listOf(
            Quotation("1", "I have no special talent. I am only passionately curious.", "Albert Einstein"),
            Quotation("2", "The best way to predict your future is to create it.", "Anonymous")
        ) + (3..20).map {
            val id = (0..99).random().toString()
            Quotation(id, "Quotation $id", "Author $id")
        }*/

    fun deleteAllQuotations() {
        //_quotationList.value = emptyList()
    }

    fun deleteQuotationAtPosition(position : Int) {
        /*val oldList = _quotationList.value?.toMutableList()
        oldList?.removeAt(position)
        _quotationList.value = oldList?.toList() ?: emptyList()*/
    }
}