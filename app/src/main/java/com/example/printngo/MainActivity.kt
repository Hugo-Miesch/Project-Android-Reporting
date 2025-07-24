package com.example.printngo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.printngo.fragments.StoreFragment
import com.example.printngo.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var nav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav = findViewById(R.id.bottom_navigation)
        loadFragment(StoreFragment())

        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_store -> loadFragment(StoreFragment())
                // Ajoute d'autres fragments ici plus tard
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame_layout, fragment)
            .commit()
    }
}
