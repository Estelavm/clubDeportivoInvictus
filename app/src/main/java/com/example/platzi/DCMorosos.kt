package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DCMorosos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dcmorosos)

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

        val gridLayout: GridLayout = findViewById(R.id.gridLayout)

        // Simulación de datos de los socios morosos
        val sociosMorosos = obtenerSociosMorosos()

        // Iterar sobre los datos y mostrarlos en el GridLayout
        for (socio in sociosMorosos) {
            val textViewNombre = TextView(this)
            textViewNombre.text = socio.nombre
            textViewNombre.textSize = 18f
            textViewNombre.setPadding(16, 16, 16, 16)
            gridLayout.addView(textViewNombre)

            val textViewDeuda = TextView(this)
            textViewDeuda.text = socio.deuda
            textViewDeuda.textSize = 16f
            textViewDeuda.setPadding(16, 16, 16, 16)
            gridLayout.addView(textViewDeuda)
        }
    }

    // Función para simular la consulta a la base de datos
    private fun obtenerSociosMorosos(): List<SocioMoroso> {
        // Simulación de datos de los socios morosos
        val sociosMorosos = mutableListOf<SocioMoroso>()
        sociosMorosos.add(SocioMoroso("Apellido1, Nombre1", "Deuda1"))
        sociosMorosos.add(SocioMoroso("Apellido2, Nombre2", "Deuda2"))
        sociosMorosos.add(SocioMoroso("Apellido3, Nombre3", "Deuda3"))
        // Agregar más datos según sea necesario

        return sociosMorosos
    }
}

// Clase simple para representar a un socio moroso
data class SocioMoroso(val nombre: String, val deuda: String)
