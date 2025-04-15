package com.sumup.challenge.toastcatalog.util

import android.view.View
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Currency
import java.util.Locale
import java.util.TimeZone

object ViewExtensions {
    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.GONE
    }
}

object CurrencyUtils {
    fun formatCurrency(price: Double, currency: String): String {
        val locale = when (currency) {
            "EUR" -> Locale.GERMANY
            else -> Locale.getDefault()
        }

        val currencyFormatter = NumberFormat.getCurrencyInstance(locale).apply {
            this.currency = Currency.getInstance(currency)
        }

        return currencyFormatter.format(price)
    }


    fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = inputFormat.parse(dateString)
        val outputFormat = SimpleDateFormat("MM/dd/yy, h:mma", Locale.getDefault())
        return outputFormat.format(date!!)
    }
}
