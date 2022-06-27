package com.example.preconoposto.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.preconoposto.data.User
import com.example.preconoposto.domain.UserAccessImpl
import com.example.preconoposto.ui.viewmodels.LoginViewModel
import com.example.preconoposto.ui.viewmodels.SignupViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.util.concurrent.TimeUnit

class SignUpViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @RelaxedMockK
    private lateinit var isSignupCorrect: Observer<Boolean>
    @RelaxedMockK
    private lateinit var userId: Observer<Long>

    private val userAccessImpl = mockk<UserAccessImpl>()

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when all variables are not empty the livedata isSignupCorrect should be true and userId should be equal to the user ID`(){
        val validUserId = 1L

        val validUserName = "name"
        val validUserSurname = "surname"
        val validUserBirthdate = "01/01/99"
        val validUserEmail = "email@email.com"
        val validUserPassword = "123456"

        val validUser = User(
            email = validUserEmail,
            password = validUserPassword,
            name = "$validUserName $validUserSurname",
            birthDate = validUserBirthdate
        )

        coEvery {
            userAccessImpl.signup(user = validUser)
        } returns validUserId

        val signupViewModel = SignupViewModel()
        signupViewModel.userAccessImpl = userAccessImpl

        signupViewModel.isSignupCorrect.observeForever(
            isSignupCorrect
        )
        signupViewModel.userId.observeForever(
            userId
        )

        signupViewModel.signupUser(
            name = validUserName,
            surname = validUserSurname,
            birthDate = validUserBirthdate,
            email = validUserEmail,
            password = validUserPassword
        )

        verify(exactly = 1){
            isSignupCorrect.onChanged(true)
        }
        verify(exactly = 1){
            userId.onChanged(validUserId)
        }
    }

    @Test
    fun `when any variable is empty the livedata isSignupCorrect should be false`(){
        val invalidUserName = ""
        val validUserSurname = "surname"
        val validUserBirthdate = "01/01/99"
        val validUserEmail = "email@email.com"
        val validUserPassword = "123456"

        val signupViewModel = SignupViewModel()
        signupViewModel.userAccessImpl = userAccessImpl

        signupViewModel.isSignupCorrect.observeForever(
            isSignupCorrect
        )

        signupViewModel.signupUser(
            name = invalidUserName,
            surname = validUserSurname,
            birthDate = validUserBirthdate,
            email = validUserEmail,
            password = validUserPassword
        )

        verify(exactly = 1){
            isSignupCorrect.onChanged(false)
        }
    }
}