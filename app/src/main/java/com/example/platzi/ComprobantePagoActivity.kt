package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class ComprobantePagoActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    private lateinit var txtNombreApellido: TextView
    private lateinit var txtFechaPago: TextView
    private lateinit var txtFormaDePago: TextView
    private lateinit var txtMonto: TextView
    private lateinit var txtObservaciones: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprobante_pago)
        db = DatabaseHelper.getInstance(this)

        // Obtener datos del intent
        val numeroDocumento = intent.getStringExtra("dni")
        val nombreApellidoPair = numeroDocumento?.let { db.getNombreYApellidoPorDNI(it) }
        val nombreApellido = nombreApellidoPair?.let { "${it.first} ${it.second}" }
        val formaDePago = intent.getStringExtra("formaDePago")
        val monto = intent.getDoubleExtra("monto", 0.0)
        val actividadesSeleccionadas = intent.getStringExtra("actividades")

        // Inicializar las vistas utilizando findViewById
        txtNombreApellido = findViewById(R.id.txtNombreApellido)
        txtFechaPago = findViewById(R.id.txtFechaPago)
        txtFormaDePago = findViewById(R.id.txtFormaDePago)
        txtMonto = findViewById(R.id.txtMonto)
        txtObservaciones = findViewById(R.id.txtObservaciones)

        // Mostrar los datos en las vistas correspondientes
        txtNombreApellido.text = nombreApellido
        txtFormaDePago.text = "$formaDePago"
        txtMonto.text = "${String.format("%.2f", monto)}" // Formatear el monto a dos decimales
        txtObservaciones.text = "Actividades seleccionadas: $actividadesSeleccionadas"

        // Obtener y mostrar la fecha actual
        val fechaActual = obtenerFechaActual()
        txtFechaPago.text = "$fechaActual"

        val atrasButton: Button = findViewById(R.id.btn_atras)
        atrasButton.setOnClickListener {
            val intent = Intent(this, FARegistroDeActividad::class.java)
            startActivity(intent)
        }
    }

    private fun obtenerFechaActual(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
