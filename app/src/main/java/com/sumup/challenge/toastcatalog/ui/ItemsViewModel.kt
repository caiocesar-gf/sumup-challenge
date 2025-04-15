package com.sumup.challenge.toastcatalog.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumup.challenge.toastcatalog.data.ItemRepository
import com.sumup.challenge.toastcatalog.data.ItemResponse
import com.sumup.challenge.toastcatalog.network.Networking.launchFromNetwork
import com.sumup.challenge.toastcatalog.util.Result

class ItemsViewModel(
    private val repository: ItemRepository
) : ViewModel() {

    private val _itemsState = MutableLiveData<Result<List<ItemResponse>>>()
    val itemsState: LiveData<Result<List<ItemResponse>>> = _itemsState


    fun fetchItems() {
        launchFromNetwork(
            request = { repository.getItems() },
            onResult = { _itemsState.value = it }
        )
    }
}