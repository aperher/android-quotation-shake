package dadm.aperher.QuotationShake.ui.favourites


import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dadm.aperher.QuotationShake.R

class DeleteAllDialogFragment() : DialogFragment() {

    private val viewmodel: FavouritesViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_all_quotations))
            .setMessage(getString(R.string.delete_all_quotes_mssg))
            .setPositiveButton(getString(R.string.delete_fav_quotations)) { _, _ ->
                viewmodel.deleteAllQuotations()
            }
            .setNegativeButton(getString(R.string.cancel_operation)) { _, _ -> }
            .create()
    }
}