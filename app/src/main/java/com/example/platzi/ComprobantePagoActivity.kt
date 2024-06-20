package com.example.platzi

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.platzi.R

class ComprobantePagoActivity : AppCompatActivity() {

    private lateinit var txtNumeroDocumento: TextView
    private lateinit var txtFecha: TextView
    private lateinit var txtEmisor: TextView
    private lateinit var txtReceptor: TextView
    private lateinit var txtDetalle: TextView
    private lateinit var txtMonto: TextView
    private lateinit var txtFormaDePago: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comprobante_pago)

        // Obtener datos del intent
        val numeroDocumento = intent.getStringExtra("dni")
        val nombreApellido = intent.getStringExtra("nombreApellido")
        val fechaPago = intent.getStringExtra("fechaPago") // Aquí se debería pasar la fecha actual
        val formaDePago = intent.getStringExtra("formaDePago")
        val monto = intent.getDoubleExtra("monto", 0.0)
        val actividadesSeleccionadas = intent.getStringExtra("actividades")

        // Inicializar las vistas utilizando findViewById
        txtNumeroDocumento = findViewById(R.id.txtNumeroDocumento)
        txtFecha = findViewById(R.id.txtFecha)
        txtEmisor = findViewById(R.id.txtEmisor)
        txtReceptor = findViewById(R.id.txtReceptor)
        txtDetalle = findViewById(R.id.txtDetalle)
        txtMonto = findViewById(R.id.txtMonto)
        txtFormaDePago = findViewById(R.id.txtFormaDePago)

        // Mostrar los datos en las vistas correspondientes
        txtNumeroDocumento.text = "Número de Documento: $numeroDocumento"
        txtFecha.text = "Fecha de Pago: $fechaPago"
        txtEmisor.text = "Datos del Emisor: " // Aquí puedes mostrar detalles del emisor si es necesario
        txtReceptor.text = "Datos del Receptor: " // Aquí puedes mostrar detalles del receptor si es necesario
        txtDetalle.text = "Actividades: $actividadesSeleccionadas"
        txtMonto.text = "Monto: ${String.format("%.2f", monto)}" // Formatear el monto a dos decimales
        txtFormaDePago.text = "Forma de Pago: $formaDePago"

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val fechaActual = dateFormat.format(calendar.time)
        txtFecha.text = "Fecha de Pago: $fechaActual"
    }
}
