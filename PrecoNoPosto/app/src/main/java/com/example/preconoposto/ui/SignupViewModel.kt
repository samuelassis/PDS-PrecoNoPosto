package com.example.preconoposto.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.data.User
import com.example.preconoposto.domain.UserAccessImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel: ViewModel() {

    val isSignupCorrect = MutableLiveData<Boolean>()
    val userId = MutableLiveData<Long>()

    lateinit var userAccessImpl: UserAccessImpl

    fun signupUser(
        name: String,
        surname: String,
        birthDate: String,
        email: String,
        password: String
    ) {
        val user = User(
            email = email,
            password = password,
            name = "$name $surname",
            birthDate = birthDate
        )
        if(
            email.isEmpty() ||
            password.isEmpty() ||
            name.isEmpty() ||
            surname.isEmpty() ||
            birthDate.isEmpty()
        )  isSignupCorrect.postValue(false)

        else CoroutineScope(Dispatchers.IO).launch{
            val newId = userAccessImpl.signup(user)
            userId.postValue(newId)
            isSignupCorrect.postValue(true)
        }
    }
}