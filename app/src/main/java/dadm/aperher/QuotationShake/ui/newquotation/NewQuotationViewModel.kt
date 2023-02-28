package dadm.aperher.QuotationShake.ui.newquotation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewQuotationViewModel : ViewModel() {
    private val _username
        get() = MutableLiveData<String>(getUserName())

    private val username
        get() = LiveData<String>(_username)

    private fun getUserName() : String {
        return setOf("Alice", "Bob", "Charlie", "David", "Emma").random()
    }
}