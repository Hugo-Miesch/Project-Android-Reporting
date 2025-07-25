package com.example.printngo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var nav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav = findViewById(R.id.bottom_navigation)
        val accountBtn = findViewById<ImageButton>(R.id.accountButton)

        loadFragment(StoreFragment())

        nav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_store -> loadFragment(StoreFragment())
                R.id.nav_printers -> loadFragment(PrintersFragment())
            }
            true
        }

        accountBtn.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame_layout, fragment)
            .commit()
    }
}
