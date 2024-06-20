package com.example.platzi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Encontrar el botón en el layout
        val usernameEditText = findViewById<EditText>(R.id.editText2)
        val passwordEditText = findViewById<EditText>(R.id.editText)
        val loginButton: Button = findViewById(R.id.btn_login)

        db = DatabaseHelper.getInstance(this)

        // Configurar el listener para el botón
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (db.validateUser(username, password)) {
                val intent = Intent(this, BPrincipalActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

