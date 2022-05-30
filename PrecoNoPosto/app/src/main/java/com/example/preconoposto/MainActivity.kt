package com.example.preconoposto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.preconoposto.ui.GasStationDetailsFragment
import com.example.preconoposto.ui.LoginFragment
import com.example.preconoposto.ui.main.MainFragment

// Adaptador de entrada

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GasStationDetailsFragment.newInstance())
                .commitNow()
        }
    }
}