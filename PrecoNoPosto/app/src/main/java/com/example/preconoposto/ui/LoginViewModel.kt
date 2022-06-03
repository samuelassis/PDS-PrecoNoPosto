package com.example.preconoposto.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.User
import com.example.preconoposto.domain.UserAccessImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    val isLoginCorrect: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var userAccessImpl: UserAccessImpl

    fun login(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            if(userAccessImpl.login(user) != null) isLoginCorrect.postValue(true)
            else isLoginCorrect.postValue(false)
        }
    }
}