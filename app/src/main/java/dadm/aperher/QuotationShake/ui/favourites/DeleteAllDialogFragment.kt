package dadm.aperher.QuotationShake.ui.favourites


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import dadm.aperher.QuotationShake.R
import dadm.aperher.QuotationShake.ui.MainActivity

class DeleteAllDialogFragment(private val buttonAction: ButtonAction) : DialogFragment() {
    interface ButtonAction {
        fun positiveAction()
        fun negativeAction()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        return AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.delete_all_quotations))
            .setMessage(getString(R.string.delete_all_quotes_mssg))
            .setPositiveButton(getString(R.string.delete_fav_quotations)) { _, _ ->
                buttonAction.positiveAction()
            }
            .setNegativeButton(getString(R.string.cancel_operation)) { _, _ ->
                buttonAction.negativeAction()
            }
            .create()
    }
}