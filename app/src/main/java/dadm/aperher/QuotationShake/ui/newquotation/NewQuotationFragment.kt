package dadm.aperher.QuotationShake.ui.newquotation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.google.android.material.snackbar.Snackbar
import dadm.aperher.QuotationShake.R
import dadm.aperher.QuotationShake.databinding.FragmentNewQuotationBinding
import dadm.aperher.QuotationShake.data.utils.NoInternetException
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewQuotationFragment : Fragment(R.layout.fragment_new_quotation), MenuProvider {
    private var _binding : FragmentNewQuotationBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: NewQuotationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewQuotationBinding.bind(view)

        viewModel.username.observe(viewLifecycleOwner) { username : String ->
            binding.tvWelcomeMessage.text = getString(R.string.welcome, username)
        }

        viewModel.quotation.observe(viewLifecycleOwner) {
            binding.tvQuotationText.text = it?.text
            binding.tvQuotationAuthor.text = it?.author ?: "Anonymous"
        }

        viewModel.isLoadingData.observe(viewLifecycleOwner) { isLoading : Boolean ->
            binding.swipeToRefresh.isRefreshing = isLoading
        }

        viewModel.isGreetingsVisible.observe(viewLifecycleOwner) { isVisible : Boolean ->
            binding.tvWelcomeMessage.visibility = if(isVisible) View.VISIBLE else View.GONE
        }

        viewModel.isFavVisible.observe(viewLifecycleOwner) { isVisible ->
            binding.btnAddToFav.isVisible = isVisible
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                if (error is NoInternetException)
                    Snackbar.make(requireContext(), view, getString(R.string.internet_exception), Snackbar.LENGTH_SHORT).show()
                else
                    Snackbar.make(requireContext(), view, error.message ?: "Unknown error", Snackbar.LENGTH_SHORT).show()
                viewModel.resetError()
            }
        }

        binding.swipeToRefresh.setOnRefreshListener { getNewQuotation() }
        binding.btnAddToFav.setOnClickListener { viewModel.addToFavourite() }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getNewQuotation() {
        viewModel.getNewQuotation()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_new_quotation, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId != R.id.refresh)
            return false

        getNewQuotation()
        return true
    }
}