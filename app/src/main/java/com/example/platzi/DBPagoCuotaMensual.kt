package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DBPagoCuotaMensual : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dbpago_cuota_mensual)

        val logoButton: ImageView = findViewById(R.id.logo)

        logoButton.setOnClickListener {
            val intent = Intent(this, BPrincipalActivity::class.java)
            startActivity(intent)
        }

        val atrasButton: Button = findViewById(R.id.btn_atras)

        atrasButton.setOnClickListener {
            val intent = Intent(this, DAPagoSocio::class.java)
            startActivity(intent)
        }
    }
}