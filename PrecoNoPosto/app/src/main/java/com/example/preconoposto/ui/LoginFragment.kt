package com.example.preconoposto.ui

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.navigation.fragment.findNavController
import com.example.preconoposto.BottomNavigationActivity
import com.example.preconoposto.data.User
import com.example.preconoposto.R
import com.example.preconoposto.database.AppDatabase
import com.example.preconoposto.database.dataStore
import com.example.preconoposto.database.loggedUserIdPreference
import com.example.preconoposto.databinding.FragmentLoginBinding
import com.example.preconoposto.domain.UserAccessImpl
import com.example.preconoposto.ui.viewmodels.LoginViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var loginEnterButton: MaterialButton
    private lateinit var loginEmailTiet: TextInputEditText
    private lateinit var loginPasswordTiet: TextInputEditText
    private lateinit var loginSignInMb: TextView

    private lateinit var loginViewModel: LoginViewModel

    private val userDao by lazy {
        AppDatabase.getInstance(this.requireContext()).userDao
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.userAccessImpl = UserAccessImpl(userDao)

        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
        setupObservers()
        setupListeners()
    }

    private fun setupViews(view: View) {
        loginEnterButton = view.findViewById(R.id.loginEnterButton)
        loginEmailTiet = view.findViewById(R.id.loginEmailTiet)
        loginPasswordTiet = view.findViewById(R.id.loginPasswordTiet)
        loginSignInMb = view.findViewById(R.id.loginSignInMb)
    }

    private fun setupObservers() {
        loginViewModel.loggedUser.observe(viewLifecycleOwner) { loggedUser ->
            if (loggedUser != null){
                goToHomePage()
            }
            else
                Toast.makeText(
                    this.requireContext(),
                    "Usuário ou senha incorretos",
                    Toast.LENGTH_SHORT
                ).show()
        }
    }

    private fun goToHomePage() {
        CoroutineScope(Dispatchers.IO).launch {
            requireContext().dataStore.edit { preferences ->
                preferences[loggedUserIdPreference] =
                    loginViewModel.loggedUser.value?.idUser.toString()
            }
        }
        startActivity(Intent(requireContext(), BottomNavigationActivity::class.java))
    }

    private fun setupListeners() {
        loginEnterButton.setOnClickListener {
            val user = User(
                name = "",
                email = loginEmailTiet.text.toString(),
                password = loginPasswordTiet.text.toString(),
                birthDate = ""
            )
            loginViewModel.login(user)
        }
        loginSignInMb.setOnClickListener {
            findNavController().navigate(R.id.fromLoginFragmentToSignupFragment)
        }
    }
}