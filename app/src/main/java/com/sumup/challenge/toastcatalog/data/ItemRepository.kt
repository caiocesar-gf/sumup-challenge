package com.sumup.challenge.toastcatalog.data


interface ItemRepository {
    suspend fun getItems(): List<ItemResponse>
}

