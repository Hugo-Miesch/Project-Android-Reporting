package com.example.printngo

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.printngo.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.core.content.edit


class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                mockLogin(email, password)
            } else {
                Toast.makeText(this, "Remplis tous les champs", Toast.LENGTH_SHORT).show()
            }
            val crashButton = Button(this)
            crashButton.text = getString(R.string.test_crash)
            crashButton.setOnClickListener {
                throw RuntimeException("Test Crash") // Force a crash
            }

            addContentView(crashButton, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT))
        }
    }

    private fun mockLogin(email: String, password: String) {
        RetrofitInstance.api.getAllUsers().enqueue(object : Callback<List<LoginUser>> {
            override fun onResponse(
                call: Call<List<LoginUser>>,
                response: Response<List<LoginUser>>
            ) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    val matched = users.find { it.email == email && it.password == password }

                    if (matched != null) {
                        val prefs = getSharedPreferences("auth", MODE_PRIVATE)
                        prefs.edit { putString("email", matched.email) }

                        Toast.makeText(this@LoginActivity, "Bienvenue ${matched.email}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish() // pour ne pas revenir en arrière sur le login

                    } else {
                        Toast.makeText(this@LoginActivity, "Identifiants incorrects", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Erreur serveur : ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<LoginUser>>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Erreur réseau : ${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }
        })
    }


}