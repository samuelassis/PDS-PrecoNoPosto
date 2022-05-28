package com.example.preconoposto.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.preconoposto.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel

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

//    private val viewModel: SignupViewModel by viewModel{
//        parametersOf(SignupUserImpl(UserRepository(UserDao)))
//    }

    private val viewModel: SignupViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            signupNameEditText.text.toString(),
            signupSurnameEditText.text.toString(),
            signupBirthDateEditText.text.toString(),
            signupEmailEditText.text.toString(),
            signupPasswordEditText.text.toString()
        )
    }

}