package dadm.aperher.QuotationShake.ui.favourites


import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import dadm.aperher.QuotationShake.ui.MainActivity

class DeleteAllDialogFragment(private val buttonAction: ButtonAction) : DialogFragment() {
    interface ButtonAction {
        fun positiveAction()
        fun negativeAction()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        return AlertDialog.Builder(requireContext())
            .setTitle("Delete all favourite quotations")
            .setMessage("Do you really want to delete all quotations?")
            .setPositiveButton("Delete") { _, _ ->
                buttonAction.positiveAction()
            }
            .setNegativeButton("Cancel") { _, _ ->
                buttonAction.negativeAction()
            }
            .create()
    }
}