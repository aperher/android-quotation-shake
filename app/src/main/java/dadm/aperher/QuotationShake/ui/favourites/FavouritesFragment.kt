package dadm.aperher.QuotationShake.ui.favourites

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dadm.aperher.QuotationShake.R
import dadm.aperher.QuotationShake.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment(R.layout.fragment_favourites),
    DeleteAllDialogFragment.ButtonAction, MenuProvider {
    private var _binding: FragmentFavouritesBinding? = null
    private val binding
        get() = _binding!!

    private val viewmodel: FavouritesViewModel by viewModels()

    private val touchHelper: ItemTouchHelper =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun isLongPressDragEnabled(): Boolean {
                return false
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewmodel.deleteQuotationAtPosition(viewHolder.adapterPosition)
            }
        })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)

        val adapter = QuotationListAdapter(object : QuotationListAdapter.ItemClicked {
            override fun onClick(author: String) {
                if (author == "Anonymous") {
                    Snackbar.make(requireContext(), view,  getString(R.string.anonymous_author), Snackbar.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Special:Search?search=$author"))
                    try {
                        startActivity(intent)
                    } catch(e : android.content.ActivityNotFoundException) {
                        Snackbar.make(requireContext(), view, getString(R.string.unable_to_complete_action), Snackbar.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.recyclerView.adapter = adapter
        viewmodel.quotationList.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        viewmodel.isDeleteItemVisible.observe(viewLifecycleOwner) {
            requireActivity().invalidateMenu()
        }

        touchHelper.attachToRecyclerView(binding.recyclerView)

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

        DeleteAllDialogFragment(this).show(childFragmentManager, null) //Se podría haber hecho tambíen con el object :
        return true
    }

    override fun onPrepareMenu(menu: Menu) {
        super.onPrepareMenu(menu)

        menu.findItem(R.id.deleteFavs).isVisible = viewmodel.isDeleteItemVisible.value!!
    }
}