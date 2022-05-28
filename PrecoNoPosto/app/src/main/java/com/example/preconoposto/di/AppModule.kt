package com.example.preconoposto.di

import com.example.preconoposto.domain.SignupUserImpl
import com.example.preconoposto.repository.UserRepositoryImpl
import com.example.preconoposto.room.UserDatabase
import com.example.preconoposto.ui.SignupViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignupViewModel(get()) }
}

val repositoryModule = module {
    single { UserRepositoryImpl(get()) }
}

val domainModule = module {
    single { SignupUserImpl(get()) }
}

val daoModule = module {
    single { UserDatabase.getInstance(androidContext()).userDao }
}