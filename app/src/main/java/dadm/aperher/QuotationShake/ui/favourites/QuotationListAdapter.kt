package dadm.aperher.QuotationShake.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dadm.aperher.QuotationShake.databinding.QuotationItemBinding
import dadm.aperher.QuotationShake.domain.model.Quotation

class QuotationListAdapter(private val itemClicked: ItemClicked) :
    ListAdapter<Quotation, QuotationListAdapter.ViewHolder>(QuotationDiff) {
    interface ItemClicked {
        fun onClick(author: String)
    }

    object QuotationDiff : DiffUtil.ItemCallback<Quotation>() {
        override fun areItemsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Quotation, newItem: Quotation): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: QuotationItemBinding = QuotationItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: QuotationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                itemClicked.onClick(binding.tvAuthor.text.toString())
            }
        }

        fun bind(quotation: Quotation) {
            binding.tvText.text = quotation.text
            binding.tvAuthor.text = quotation.author
        }
    }
}