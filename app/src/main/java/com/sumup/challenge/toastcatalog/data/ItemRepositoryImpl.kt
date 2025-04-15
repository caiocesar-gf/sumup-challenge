    package com.sumup.challenge.toastcatalog.data

    import android.util.Log
    import com.sumup.challenge.toastcatalog.network.Service

    class ItemRepositoryImpl(private val service: Service) : ItemRepository {
        override suspend fun getItems(): List<ItemResponse> {
            Log.d("Repository", "getItems() chamando service")
            return service.getItems()
        }
    }