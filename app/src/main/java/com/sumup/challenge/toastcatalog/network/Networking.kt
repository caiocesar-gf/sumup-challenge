package com.sumup.challenge.toastcatalog.network


import androidx.lifecycle.*
import com.sumup.challenge.toastcatalog.util.Result
import kotlinx.coroutines.launch

object Networking {

    fun <T> ViewModel.launchFromNetwork(
        request: suspend () -> T,
        onResult: (Result<T>) -> Unit
    ) {
        viewModelScope.launch {
            onResult(Result.Loading)
            try {
                val result = request()
                onResult(Result.Success(result))
            } catch (e: Exception) {
                onResult(Result.Error(e.message ?: "Erro desconhecido"))
            }
        }
    }
}