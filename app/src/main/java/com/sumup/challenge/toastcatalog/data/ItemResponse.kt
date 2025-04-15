package com.sumup.challenge.toastcatalog.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ItemResponse(
    val id: Int,
    val name: String,
    val price: Double,
    val currency: String,
    val last_sold: String
) : Parcelable