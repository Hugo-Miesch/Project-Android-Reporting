package com.example.printngo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        val titleText = findViewById<TextView>(R.id.accountTitle)
        val emailText = findViewById<TextView>(R.id.accountEmail)
        val logoutButton = findViewById<Button>(R.id.logoutButton)

        // Récupère l'email stocké après login
        val email = getSharedPreferences("auth", MODE_PRIVATE)
            .getString("email", "Non connecté")

        emailText.text = email

        logoutButton.setOnClickListener {
            // Efface les infos de session
            getSharedPreferences("auth", MODE_PRIVATE)
                .edit()
                .remove("email")
                .apply()

            // Redirige vers LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
