package com.example.preconoposto.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.User
import com.example.preconoposto.domain.LoginUserImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    val isLoginCorrect: MutableLiveData<Boolean> = MutableLiveData(true)

//    lateinit var loginUser: LoginUserImpl

    fun login(user: User, loginUser: LoginUserImpl) {
        CoroutineScope(Dispatchers.IO).launch {
            if(loginUser.login(user) == null) isLoginCorrect.postValue(true)
            else isLoginCorrect.postValue(false)
        }
    }
}