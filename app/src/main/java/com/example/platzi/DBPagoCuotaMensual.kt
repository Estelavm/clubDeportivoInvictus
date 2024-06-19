package com.example.platzi

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DBPagoCuotaMensual : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dbpago_cuota_mensual)
        db = DatabaseHelper.getInstance(this)

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

        val btnPagar: Button = findViewById(R.id.btn_pagar)
        val editTextDni: EditText = findViewById(R.id.editText1)
        val editTextMonto: EditText = findViewById(R.id.editText2)

        editTextDni.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val dni = editTextDni.text.toString().trim()
                if (dni.isNotEmpty()) {
                    // Obtener la cuota mensual del usuario
                    val monthlyFee = db.getMonthlyFee(dni)
                    editTextMonto.setText(monthlyFee.toString())
                }
            }
        }

        btnPagar.setOnClickListener {
            val dni = editTextDni.text.toString().trim()
            val monto = editTextMonto.text.toString().toDoubleOrNull() ?: 0.0

            if (dni.isNotEmpty() && monto > 0) {
                // Verificar si el usuario existe en la base de datos
                val user = db.getUserByDNI(dni)
                if (user != null) {
                    // Actualizar el estado de pago y la fecha de último pago
                    db.updatePaymentStatus(dni)
                    // Registrar el pago mensual en la base de datos
                    val id = db.insertUserData(
                        user.name,
                        user.lastName,
                        user.docType,
                        dni,
                        user.userType
                    )

                    // Actualizar la UI o mostrar un mensaje de éxito
                    Toast.makeText(this, "Pago realizado con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "Ingrese un DNI válido y un monto mayor a cero",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val comprobanteButton = findViewById<Button>(R.id.btn_comprobante)
        comprobanteButton.setOnClickListener {
            val dni = editTextDni.text.toString().trim()
            val user = db.getUserByDNI(dni)
            val monto = editTextMonto.text.toString().toDoubleOrNull() ?: 0.0 // Definir monto aquí

            if (user != null) {
                val intent = Intent(this, ComprobantePagoActivity::class.java).apply {
                    putExtra("numeroDocumento", dni) // Usamos el DNI como número de documento
                    putExtra("nombreDocumento", "${user.name} ${user.lastName}")
                    putExtra("fecha", getCurrentDate())
                    putExtra("emisor", "Datos del Emisor") // Puedes ajustar estos valores según tus necesidades
                    putExtra("receptor", "Datos del Receptor")
                    putExtra("detalle", "Pago mensual de cuota")
                    putExtra("monto", monto)
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(Date())
    }
}
