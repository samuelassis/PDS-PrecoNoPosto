package com.example.preconoposto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.preconoposto.ui.LoginFragment
import com.example.preconoposto.ui.SignupFragment

// Adaptador de entrada

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SignupFragment.newInstance())
                .commitNow()
        }
    }
}