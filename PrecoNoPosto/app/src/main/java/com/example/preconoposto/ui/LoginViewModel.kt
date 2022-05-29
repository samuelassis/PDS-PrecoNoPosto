package com.example.preconoposto.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.UserEntity
import com.example.preconoposto.domain.LoginUserImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {

    val isLoginCorrect: MutableLiveData<Boolean> = MutableLiveData(true)

    fun login(user: UserEntity, loginUser: LoginUserImpl) {
        CoroutineScope(Dispatchers.IO).launch {
            if(loginUser.login(user) == null) isLoginCorrect.postValue(true)
            else isLoginCorrect.postValue(false)
        }
    }
}