package com.example.preconoposto.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.preconoposto.MainCoroutineRule
import com.example.preconoposto.data.User
import com.example.preconoposto.repository.UserRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class UserAccessImplTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rule = Timeout(1, TimeUnit.MINUTES)

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @RelaxedMockK
    val userRepository = mockk<UserRepository>()

    @Test
    fun `when calling login with a valid email and password it should return the user of that credential`() =
        runBlocking {
            val validUserEmail = "email@email.com"
            val validUserPassword = "123456"

            val validUser = User(
                idUser = 1L,
                email = validUserEmail,
                password = validUserPassword,
                name = "Name Surname",
                birthDate = "01/01/99"
            )

            coEvery {
                userRepository.getUser(email = validUserEmail, password = validUserPassword)
            } returns validUser

            val userAccessImpl = UserAccessImpl(userRepository)
            val returnedUser = userAccessImpl.login(validUser)
            assertEquals(validUser, returnedUser)
        }

    @Test
    fun `when calling login with a invalid email and password it should return null`() =
        runBlocking {
            val invalidUserEmail = "notRegistered@email.com"
            val invalidUserPassword = "000"

            val invalidUser = User(
                idUser = 1L,
                email = invalidUserEmail,
                password = invalidUserPassword,
                name = "Name Surname",
                birthDate = "01/01/99"
            )

            coEvery {
                userRepository.getUser(email = invalidUserEmail, password = invalidUserPassword)
            } returns null

            val userAccessImpl = UserAccessImpl(userRepository)
            val returnedUser = userAccessImpl.login(invalidUser)
            assertEquals(null, returnedUser)
        }

    @Test
    fun `when calling signup it should return the userId`() =
        runBlocking {

            val validUser = User(
                idUser = 1L,
                email = "validEmail@gmail.com",
                password = "validPassword",
                name = "Name Surname",
                birthDate = "01/01/99"
            )

            coEvery {
                userRepository.save(validUser)
            } returns validUser.idUser

            val userAccessImpl = UserAccessImpl(userRepository)
            val returnedUser = userAccessImpl.signup(validUser)
            assertEquals(validUser.idUser, returnedUser)
        }
}