package com.example.preconoposto.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.preconoposto.data.User
import com.example.preconoposto.R
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.domain.LoginUserImpl
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var loginEnterButton: MaterialButton
    private lateinit var loginEmailTiet: TextInputEditText
    private lateinit var loginPasswordTiet: TextInputEditText

    private lateinit var loginViewModel: LoginViewModel

    private val db = AppDatabase.getInstance(this.requireContext())
    private val loginUserImpl = LoginUserImpl(db.userDao)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.loginUser = loginUserImpl

        setupObservers()
        setupListeners()
    }

    private fun setupObservers(){
        loginViewModel.isLoginCorrect.observe(viewLifecycleOwner){ isLoginCorrect ->
            if(isLoginCorrect)
                Log.i("login", "login feito com sucesso")
            else
                Log.i("login", "login não foi efetuado")
        }
    }

    private fun setupListeners(){
        loginEnterButton.setOnClickListener {
            val user = User(
                idUser=0L,
                name = "",
                email=loginEmailTiet.text.toString(),
                password=loginPasswordTiet.text.toString(),
                birthday = "31/12/1999"
            )
            loginViewModel.login(user)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginEnterButton = view.findViewById(R.id.loginEnterButton)
        loginEmailTiet = view.findViewById(R.id.loginEmailTiet)
        loginPasswordTiet = view.findViewById(R.id.loginPasswordTiet)
    }

}