package com.example.preconoposto.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.preconoposto.data.User
import com.example.preconoposto.domain.UserAccessImpl
import com.example.preconoposto.ui.viewmodels.LoginViewModel
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.util.concurrent.TimeUnit

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @RelaxedMockK
    private lateinit var loggedUserTest: Observer<User?>

    private val userAccessImpl = mockk<UserAccessImpl>()

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when user exists it should set the loggedUser variable with it`(){
        val expectedUser = User(
            idUser = 1,
            email = "a",
            password = "a",
            name = "Usuário Um",
            birthDate = "31/02/99"
        )

        coEvery {
            userAccessImpl.login(user = expectedUser)
        } returns expectedUser

        val loginViewModel = LoginViewModel()
        loginViewModel.userAccessImpl = userAccessImpl

        loginViewModel.loggedUser.observeForever(
            loggedUserTest
        )
        loginViewModel.login(expectedUser)

        verify(exactly = 1){
            loggedUserTest.onChanged(expectedUser)
        }
    }

    @Test
    fun `when user does not exists it should set the loggedUser variable to null`(){
        val unregisteredUser = User(
            idUser = 1,
            email = "a",
            password = "a",
            name = "Usuário Um",
            birthDate = "31/02/99"
        )

        coEvery {
            userAccessImpl.login(user = unregisteredUser)
        } returns null

        val loginViewModel = LoginViewModel()
        loginViewModel.userAccessImpl = userAccessImpl

        loginViewModel.loggedUser.observeForever(
            loggedUserTest
        )
        loginViewModel.login(unregisteredUser)

        verify(exactly = 1){
            loggedUserTest.onChanged(null)
        }
    }
}