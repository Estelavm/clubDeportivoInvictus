package com.example.platzi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ComprobantePagoActivity : AppCompatActivity() {

    private lateinit var txtNumeroDocumento: TextView
    private lateinit var txtNombreDocumento: TextView
    private lateinit var txtFecha: TextView
    private lateinit var txtEmisor: TextView
    private lateinit var txtReceptor: TextView
    private lateinit var txtDetalle: TextView
    private lateinit var txtMonto: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprobante_pago)

        // Obtener datos del intent
        val numeroDocumento = intent.getStringExtra("numeroDocumento")
        val nombreDocumento = intent.getStringExtra("nombreDocumento")
        val fecha = intent.getStringExtra("fecha")
        val emisor = intent.getStringExtra("emisor")
        val receptor = intent.getStringExtra("receptor")
        val detalle = intent.getStringExtra("detalle")
        val monto = intent.getDoubleExtra("monto", 0.0)

        // Inicializar las vistas utilizando findViewById
        txtNumeroDocumento = findViewById(R.id.txtNumeroDocumento)
        txtNombreDocumento = findViewById(R.id.txtNombreDocumento)
        txtFecha = findViewById(R.id.txtFecha)
        txtEmisor = findViewById(R.id.txtEmisor)
        txtReceptor = findViewById(R.id.txtReceptor)
        txtDetalle = findViewById(R.id.txtDetalle)
        txtMonto = findViewById(R.id.txtMonto)

        // Mostrar los datos en las vistas correspondientes
        txtNumeroDocumento.text = numeroDocumento
        txtNombreDocumento.text = nombreDocumento
        txtFecha.text = fecha
        txtEmisor.text = emisor
        txtReceptor.text = receptor
        txtDetalle.text = detalle
        txtMonto.text = String.format("%.2f", monto) // Formatear el monto a dos decimales
    }
}
