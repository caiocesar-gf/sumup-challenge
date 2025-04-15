package com.sumup.challenge.toastcatalog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sumup.challenge.toastcatalog.R
import com.sumup.challenge.toastcatalog.data.ItemResponse
import com.sumup.challenge.toastcatalog.databinding.ItemBinding
import com.sumup.challenge.toastcatalog.util.CurrencyUtils.formatCurrency
import com.sumup.challenge.toastcatalog.util.CurrencyUtils.formatDate
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ItemsAdapter : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private val items = mutableListOf<ItemResponse>()
    var onItemClickListener: ((ItemResponse) -> Unit)? = null


    fun submitList(newItems: List<ItemResponse>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemResponse) {
            binding.tvItemName.text = item.name
            binding.tvItemPrice.text = formatCurrency(item.price, item.currency)
            binding.tvItemDate.text = formatDate(item.last_sold)
            binding.tvItemId.text = item.id.toString()

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }

            binding.ivItemImage.setImageResource(R.drawable.baseline_circle_24)

        }
    }
}
