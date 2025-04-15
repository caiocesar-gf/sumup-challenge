package com.sumup.challenge.toastcatalog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sumup.challenge.toastcatalog.databinding.FragmentItemsBinding
import com.sumup.challenge.toastcatalog.util.Result
import com.sumup.challenge.toastcatalog.util.ViewExtensions.hide
import com.sumup.challenge.toastcatalog.util.ViewExtensions.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsFragment : Fragment() {

    private var _binding: FragmentItemsBinding? = null
    private val binding get() = _binding!!

    private val adapter = ItemsAdapter()
    private val viewModel: ItemsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        observeViewModel()
        viewModel.fetchItems()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        adapter.onItemClickListener = { item ->
            val action = ItemsFragmentDirections.actionItemsFragmentToItemDetailFragment(item)
            findNavController().navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.itemsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Result.Loading -> binding.progressBar.show()
                is Result.Success -> {
                    binding.progressBar.hide()
                    adapter.submitList(state.data)
                }
                is Result.Error -> {
                    binding.progressBar.hide()
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
