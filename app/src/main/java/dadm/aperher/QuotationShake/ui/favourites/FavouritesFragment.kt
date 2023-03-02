package dadm.aperher.QuotationShake.ui.favourites

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import dadm.aperher.QuotationShake.R
import dadm.aperher.QuotationShake.databinding.FragmentFavouritesBinding
import dadm.aperher.QuotationShake.databinding.FragmentNewQuotationBinding

class FavouritesFragment : Fragment(R.layout.fragment_favourites), DeleteAllDialogFragment.ButtonAction, MenuProvider {
    private var _binding : FragmentFavouritesBinding? = null
    private val binding
        get() = _binding!!

    private val viewmodel : FavouritesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        val adapter = QuotationListAdapter()
        binding.recyclerView.adapter = adapter
        viewmodel.quotationList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        viewmodel.isDeleteItemVisible.observe(viewLifecycleOwner) {
            requireActivity().invalidateMenu()
        }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun positiveAction() {
        viewmodel.deleteAllQuotations()
    }

    override fun negativeAction() {}
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_favourites, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId != R.id.deleteFavs)
            return false

        DeleteAllDialogFragment(this).show(childFragmentManager, null)
        return true
    }

    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)

        menu.findItem(R.id.deleteFavs).isVisible = viewmodel.isDeleteItemVisible.value!!
    }
}