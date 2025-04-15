package com.sumup.challenge.toastcatalog.di


import com.sumup.challenge.toastcatalog.data.ItemRepository
import com.sumup.challenge.toastcatalog.data.ItemRepositoryImpl
import com.sumup.challenge.toastcatalog.ui.ItemsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ItemsViewModel(get()) }
    single<ItemRepository> { ItemRepositoryImpl(get()) }
}