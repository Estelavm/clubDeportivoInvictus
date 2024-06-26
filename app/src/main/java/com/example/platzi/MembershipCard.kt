package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MembershipCard : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membership_card)
        enableEdgeToEdge()
        db = DatabaseHelper.getInstance(this)

        val logoButton: ImageView = findViewById(R.id.logo)
        logoButton.setOnClickListener {
            startActivity(Intent(this, PrincipalActivity::class.java))
        }

        val atrasButton: Button = findViewById(R.id.btn_atras)
        atrasButton.setOnClickListener {
            startActivity(Intent(this, CollectMonthlyFee::class.java))
        }

        val textViewNombre: TextView = findViewById(R.id.textView_nombre)
        val textViewNDocumento: TextView = findViewById(R.id.textView_n_documento)
        val regDateTextView: TextView = findViewById(R.id.textView_fecha_ingreso)

        // Obtener los datos del usuario de alguna manera (por ejemplo, desde intent extras)
        val nombreCompleto = intent.getStringExtra("nombreCompleto") ?: ""
        val tipoYNumeroDocumento = intent.getStringExtra("tipoYNumeroDocumento") ?: ""
        val docNumber = intent.getStringExtra("docNumber") ?: ""

        // Aquí debes obtener el usuario desde la base de datos usando com.example.platzi.DatabaseHelper
        val user = db.getUserByDNI(docNumber)

        if (user != null) {
            // Mostrar los datos en las vistas correspondientes
            textViewNombre.text = "Nombre: ${user.name} ${user.lastName}"
            textViewNDocumento.text = "N° Documento: ${user.docType} ${user.docNumber}"
            regDateTextView.text = "Fecha de ingreso: ${user.regDate}"
        } else {
            // Manejar el caso donde el usuario no se encuentra en la base de datos
            textViewNombre.text = "Usuario no encontrado"
            textViewNDocumento.text = ""
            regDateTextView.text = ""
        }
    }
}