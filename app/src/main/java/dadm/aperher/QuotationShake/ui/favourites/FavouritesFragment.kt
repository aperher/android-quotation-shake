package dadm.aperher.QuotationShake.ui.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dadm.aperher.QuotationShake.R
import dadm.aperher.QuotationShake.databinding.FragmentFavouritesBinding
import dadm.aperher.QuotationShake.databinding.FragmentNewQuotationBinding

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private var _binding : FragmentFavouritesBinding? = null
    private val binding
        get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavouritesBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}