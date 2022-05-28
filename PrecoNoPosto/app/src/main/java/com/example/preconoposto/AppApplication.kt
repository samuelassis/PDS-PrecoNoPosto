package com.example.preconoposto

import android.app.Application
import com.example.preconoposto.di.daoModule
import com.example.preconoposto.di.domainModule
import com.example.preconoposto.di.repositoryModule
import com.example.preconoposto.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule)
            modules(repositoryModule)
            modules(daoModule)
            modules(domainModule)
        }
    }
}