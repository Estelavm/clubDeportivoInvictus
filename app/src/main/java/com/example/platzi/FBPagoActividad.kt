package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class FBPagoActividad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fbpago_actividad)

        val logoButton: ImageView = findViewById(R.id.logo)

        logoButton.setOnClickListener {
            val intent = Intent(this, BPrincipalActivity::class.java)
            startActivity(intent)
        }

        val atrasButton: Button = findViewById(R.id.btn_atras)

        atrasButton.setOnClickListener {
            val intent = Intent(this, FARegistroDeActividad::class.java)
            startActivity(intent)
        }
    }
}