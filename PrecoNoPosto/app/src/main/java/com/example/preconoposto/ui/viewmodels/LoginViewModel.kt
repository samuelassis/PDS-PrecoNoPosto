package com.example.preconoposto.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.User
import com.example.preconoposto.domain.UserAccessImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    val loggedUser: MutableLiveData<User?> = MutableLiveData()

    lateinit var userAccessImpl: UserAccessImpl

    fun login(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val loggedUserAttempt = userAccessImpl.login(user)
            loggedUser.postValue(loggedUserAttempt)
        }
    }
}