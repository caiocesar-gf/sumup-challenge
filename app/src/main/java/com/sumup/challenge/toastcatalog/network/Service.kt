package com.sumup.challenge.toastcatalog.network

import com.sumup.challenge.toastcatalog.data.ItemResponse
import retrofit2.http.GET

interface Service {
    @GET("sumup-challenges/mobile-coding-challenge-data/items")
    suspend fun getItems(): List<ItemResponse>
}