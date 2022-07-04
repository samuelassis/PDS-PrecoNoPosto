package com.example.preconoposto.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.preconoposto.R
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.databinding.FragmentSignupBinding
import com.example.preconoposto.domain.UserAccessImpl
import com.example.preconoposto.ui.viewmodels.SignupViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupFragment : Fragment() {

    lateinit var signupNameEditText: TextInputEditText
    lateinit var signupSurnameEditText: TextInputEditText
    lateinit var signupBirthDateEditText: TextInputEditText
    lateinit var signupEmailEditText: TextInputEditText
    lateinit var signupPasswordEditText: TextInputEditText
    lateinit var signupConfirmButton: MaterialButton

    private lateinit var viewModel: SignupViewModel

    private val userDao by lazy {
        AppDatabase.getInstance(this.requireContext()).userDao
    }

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]
        viewModel.userAccessImpl = UserAccessImpl(userDao)
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
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
            if (it == true) {
                Log.i("login", "signup feito com sucesso")
                requireActivity().onBackPressed()
            } else {
                showRequiredFieldAlert()
            }
        }

        setTextChangedListener(signupNameEditText, binding.signupNameLayout)
        setTextChangedListener(signupSurnameEditText, binding.signupSurnameLayout)
        setTextChangedListener(signupEmailEditText, binding.signupEmailLayout)
        setTextChangedListener(signupPasswordEditText, binding.signupPasswordLayout)

        viewModel.showBirthDateError.observe(viewLifecycleOwner) {
            if (it != 0) {
                binding.signupBirthDateLayout.error = getString(it)
                binding.signupBirthDateLayout.isErrorEnabled = true
            } else {
                binding.signupBirthDateLayout.isErrorEnabled = false
            }
        }

        signupConfirmButton.setOnClickListener {
            signupUser()
        }
    }

    fun setTextChangedListener(textInputEditText: TextInputEditText, textInputLayout: TextInputLayout) {
        textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    if (it.isBlank()) {
                        textInputLayout.error = getString(R.string.signup_required_field)
                        textInputLayout.isErrorEnabled = true
                    } else {
                        textInputLayout.isErrorEnabled = false
                    }
                }  ?: run {
                    textInputLayout.error = getString(R.string.signup_required_field)
                    textInputLayout.isErrorEnabled = true
                }
            }

        })
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

    private fun showRequiredFieldAlert(){
        val builder = AlertDialog.Builder(context)
        with(builder) {
            setTitle(getString(R.string.signup_required_field_error_title))
            setMessage(getString(R.string.signup_required_field_error_description))
            setPositiveButton("Ok") { dialog, _->
                dialog.dismiss()
            }
            show()
        }
    }

}