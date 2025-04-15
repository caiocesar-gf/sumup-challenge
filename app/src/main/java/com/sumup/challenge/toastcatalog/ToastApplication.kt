package com.sumup.challenge.toastcatalog

import android.app.Application
import com.sumup.challenge.toastcatalog.di.appModule
import com.sumup.challenge.toastcatalog.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ToastCatalogApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ToastCatalogApp)
            modules(appModule, networkModule)
        }
    }
}