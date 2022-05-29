package com.example.preconoposto.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.preconoposto.R
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.domain.LoginUserImpl
import com.example.preconoposto.domain.SignupUserImpl
import com.example.preconoposto.repository.UserRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class SignupFragment : Fragment() {

    companion object {
        fun newInstance() = SignupFragment()
    }

    lateinit var signupNameEditText: TextInputEditText
    lateinit var signupSurnameEditText: TextInputEditText
    lateinit var signupBirthDateEditText: TextInputEditText
    lateinit var signupEmailEditText: TextInputEditText
    lateinit var signupPasswordEditText: TextInputEditText
    lateinit var signupConfirmButton: MaterialButton


    private lateinit var viewModel: SignupViewModel
    private lateinit var db: AppDatabase
    private lateinit var signupUserImpl: SignupUserImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        db = AppDatabase.getInstance(this.requireContext())
        signupUserImpl = SignupUserImpl(db.userDao)
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupListeners()
    }

    private fun setupViews(view: View) {
        signupNameEditText = view.findViewById(R.id.signupNameEditText)
        signupSurnameEditText = view.findViewById(R.id.signupSurnameEditText)
        signupBirthDateEditText = view.findViewById(R.id.signupBirthDateEditText)
        signupEmailEditText = view.findViewById(R.id.signupEmailEditText)
        signupPasswordEditText = view.findViewById(R.id.signupPasswordEditText)
        signupConfirmButton = view.findViewById(R.id.signupConfirmButton)
    }

    private fun setupListeners() {
        viewModel.isSignupCorrect.observe(viewLifecycleOwner) {
            if (it == true)
                Log.i("login", "signup feito com sucesso")
            else
                Log.i("login", "signup não foi efetuado")
        }

        signupConfirmButton.setOnClickListener {
            signupUser()
        }
    }

    private fun signupUser() {
        viewModel.signupUser(
            signupUserImpl,
            signupNameEditText.text.toString(),
            signupSurnameEditText.text.toString(),
            signupBirthDateEditText.text.toString(),
            signupEmailEditText.text.toString(),
            signupPasswordEditText.text.toString()
        )
    }

}