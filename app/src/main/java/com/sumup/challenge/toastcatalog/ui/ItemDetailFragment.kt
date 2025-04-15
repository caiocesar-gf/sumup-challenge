package com.sumup.challenge.toastcatalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sumup.challenge.toastcatalog.R
import com.sumup.challenge.toastcatalog.databinding.FragmentItemDetailBinding
import com.sumup.challenge.toastcatalog.util.CurrencyUtils.formatCurrency
import com.sumup.challenge.toastcatalog.util.CurrencyUtils.formatDate

class ItemDetailFragment : Fragment() {

    private var _binding: FragmentItemDetailBinding? = null
    private val binding get() = _binding!!
    private val args: ItemDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item = args.item
        binding.tvItemName.text = item.name
        binding.tvItemPrice.text = formatCurrency(item.price, item.currency)
        binding.tvItemDate.text = formatDate(item.last_sold)
        binding.tvItemId.text = item.id.toString()
        binding.ivItemImage.setImageResource(R.drawable.baseline_circle_24)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
