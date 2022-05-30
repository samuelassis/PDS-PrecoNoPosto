package com.example.preconoposto.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.User
import com.example.preconoposto.domain.SignupUserImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel: ViewModel() {

    val isSignupCorrect = MutableLiveData(false)

    fun signupUser(
        signupUserImpl: SignupUserImpl,
        email: String,
        password: String,
        name: String,
        surname: String,
        birthDate: String
    ) {
        val user = User(
            email = email,
            password = password,
            name = name + surname,
            birthDate = birthDate
        )
        CoroutineScope(Dispatchers.IO).launch{
            signupUserImpl.signup(user)
            isSignupCorrect.postValue(true)
        }
    }
}