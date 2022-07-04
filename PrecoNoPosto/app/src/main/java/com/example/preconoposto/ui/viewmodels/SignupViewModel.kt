package com.example.preconoposto.ui.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.preconoposto.R
import com.example.preconoposto.data.User
import com.example.preconoposto.domain.UserAccessImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.time.LocalDate
import java.time.Period
import java.util.*

class SignupViewModel : ViewModel() {

    val isSignupCorrect = MutableLiveData<Boolean>()
    val userId = MutableLiveData<Long>()

    lateinit var userAccessImpl: UserAccessImpl

    val showBirthDateError = MutableLiveData(0)

    fun onAgeChange(age: CharSequence) {
        val isValidBirthDate = isValidBirthDate(age.toString())
        showBirthDateError.value = isValidBirthDate
    }

    private fun isValidBirthDate(birthDate: String): Int {
        birthDate.withIndex().forEach {
            if (it.index == 2 || it.index == 5) {
                if (it.value.toString() != "/") {
                    return R.string.inserir_campo_data_de_nascimento
                }
            }
        }

        return if (birthDate.length == 10) {
            validateAge(birthDate)
        } else {
            R.string.inserir_campo_data_de_nascimento
        }
    }

    private fun validateAge(birthDate: String): Int {
        val age = getAge(birthDate)
        return when {
            age < 0 -> R.string.data_de_nascimento_invalida
            age < 18 -> R.string.menor_de_idade
            else -> 0
        }
    }

    private fun getAge(birthDate: String): Int {
        val day = birthDate.substring(0, 2).toInt()
        val month = birthDate.substring(3, 5).toInt()
        val year = birthDate.substring(6, 10).toInt()
        val dateOfBirth = Calendar.getInstance()
        val today = Calendar.getInstance()
        dateOfBirth[year, month] = day
        var age = today[Calendar.YEAR] - dateOfBirth[Calendar.YEAR]
        if (today[Calendar.DAY_OF_YEAR] < dateOfBirth[Calendar.DAY_OF_YEAR]) {
            age--
        }
        return age
    }

    fun signupUser(
        name: String,
        surname: String,
        birthDate: String,
        email: String,
        password: String,
    ) {
        val user = User(
            email = email,
            password = password,
            name = "$name $surname",
            birthDate = birthDate
        )
        if (
            email.isEmpty() ||
            password.isEmpty() ||
            name.isEmpty() ||
            surname.isEmpty() ||
            birthDate.isEmpty()
        ) isSignupCorrect.postValue(false)
        else CoroutineScope(Dispatchers.IO).launch {
            val newId = userAccessImpl.signup(user)
            userId.postValue(newId)
            isSignupCorrect.postValue(true)
        }
    }
}