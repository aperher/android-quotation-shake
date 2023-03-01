package dadm.aperher.QuotationShake.ui.newquotation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dadm.aperher.QuotationShake.R
import dadm.aperher.QuotationShake.databinding.FragmentNewQuotationBinding

class NewQuotationFragment : Fragment(R.layout.fragment_new_quotation) {
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
            binding.tvQuotationText.text = it?.name
            binding.tvQuotationAuthor.text = it?.author ?: "Anonymous"
        }

        viewModel.isLoadingData.observe(viewLifecycleOwner) { isLoading : Boolean ->
            binding.swipeToRefresh.isRefreshing = isLoading
        }

        viewModel.isGreetingsVisible.observe(viewLifecycleOwner) { isVisible : Boolean ->
            binding.tvWelcomeMessage.visibility = if(isVisible) View.VISIBLE else View.GONE
        }

        binding.swipeToRefresh.setOnRefreshListener { viewModel.getNewQuotation() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}