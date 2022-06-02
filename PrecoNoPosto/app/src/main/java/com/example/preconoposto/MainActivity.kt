package com.example.preconoposto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.preconoposto.databinding.MainActivityBinding
import com.example.preconoposto.ui.LoginFragment
import com.example.preconoposto.ui.SignupFragment

// Adaptador de entrada

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavBar()


//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, SignupFragment.newInstance())
//                .commitNow()
//        }
    }

    private fun setupNavBar() {
        val navHostFragment = (supportFragmentManager.findFragmentById(binding.containerViewMain.id)) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfig = AppBarConfiguration(navController.graph)
        binding.mainToolbar.setupWithNavController(navController, appBarConfig)

        navController.addOnDestinationChangedListener { _, destination, args ->

            when(destination.id) {
                R.id.signupFragment -> {
                    binding.mainToolbar.visibility = View.VISIBLE
                    binding.mainToolbar.visibility = View.VISIBLE
                }
                else -> {
                    binding.mainToolbar.visibility = View.GONE
                    binding.mainToolbar.visibility = View.GONE
                }
            }
        }
    }
}