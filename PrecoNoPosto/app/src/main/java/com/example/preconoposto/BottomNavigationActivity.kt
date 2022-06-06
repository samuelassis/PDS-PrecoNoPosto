package com.example.preconoposto.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.preconoposto.R
import com.example.preconoposto.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val navHostFragment = (supportFragmentManager.findFragmentById(viewBinding.fragmentContainerView3.id)) as NavHostFragment
        val navController = navHostFragment.navController

        viewBinding.bottomNavigationView.setupWithNavController(navController)
    }
}