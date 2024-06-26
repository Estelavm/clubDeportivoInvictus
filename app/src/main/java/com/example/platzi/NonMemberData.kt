package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class NonMemberData : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_non_member_data)
        enableEdgeToEdge()

        val logoButton: ImageView = findViewById(R.id.logo)
        logoButton.setOnClickListener {
            startActivity(Intent(this, PrincipalActivity::class.java))
        }

        val atrasButton: Button = findViewById(R.id.btn_atras)
        atrasButton.setOnClickListener {
            startActivity(Intent(this, RegisterUser::class.java))
        }

            // Obtener datos del intent
            val nombreCompleto = intent.getStringExtra("nombreCompleto")
            val tipoDocumento = intent.getStringExtra("tipoDocumento")
            val numeroDocumento = intent.getStringExtra("numeroDocumento")

            // Mostrar datos en la actividad
            val textViewNombre: TextView = findViewById(R.id.textView_nombre)
            textViewNombre.text = "Nombre completo: $nombreCompleto"

            val textViewNDocumento: TextView = findViewById(R.id.textView_n_documento)
            textViewNDocumento.text = "N° de documento: $tipoDocumento - $numeroDocumento"

            val textViewFechaIngreso: TextView = findViewById(R.id.textView_fecha_ingreso)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val fechaActual = dateFormat.format(Date())
            textViewFechaIngreso.text = "Fecha: $fechaActual"
        }

    }
