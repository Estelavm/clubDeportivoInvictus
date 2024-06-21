package com.example.platzi

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DBPagoCuotaMensual : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    private lateinit var btnVerCarnet: Button

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
        btnVerCarnet = findViewById(R.id.btn_ver_carnet)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)

        editTextDni.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val dni = editTextDni.text.toString().trim()
                if (dni.isNotEmpty()) {
                    val monthlyFee = db.getMonthlyFee(dni)
                    editTextMonto.setText(monthlyFee.toString())
                }
            }
        }

        btnPagar.setOnClickListener {
            val dni = editTextDni.text.toString().trim()
            val monto = editTextMonto.text.toString().toDoubleOrNull() ?: 0.0

            if (dni.isNotEmpty() && monto > 0) {
                val user = db.getUserByDNI(dni)
                if (user != null) {
                    db.updatePaymentStatus(dni) // Actualiza el estado de pago del usuario
                    val success = db.insertOrUpdateUserData(
                        user.name,
                        user.lastName,
                        user.docType,
                        dni,
                        user.userType
                    )
                    if (success) {
                        Toast.makeText(this, "Pago realizado con éxito", Toast.LENGTH_SHORT).show()
                        btnVerCarnet.visibility = Button.VISIBLE
                    } else {
                        Toast.makeText(
                            this,
                            "Error al actualizar datos del usuario",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
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
            val monto = editTextMonto.text.toString().toDoubleOrNull() ?: 0.0

            // Obtener el nombre y apellido del usuario desde la base de datos
            val nombreApellidoPair = dni.let { db.getNombreYApellidoPorDNI(it) }
            val nombreApellido =
                nombreApellidoPair?.let { "${it.first} ${it.second}" } ?: "No encontrado"

            // Obtener la forma de pago seleccionada
            val formaDePago = when (radioGroup.checkedRadioButtonId) {
                R.id.radioButton -> "Efectivo"
                R.id.radioButton2 -> "Tarjeta"
                else -> "No especificado"
            }

            if (nombreApellido != "No encontrado") {
                val intent = Intent(this, MembershipPaymentReceipt::class.java).apply {
                    putExtra("numeroDocumento", dni)
                    putExtra("nombreApellido", nombreApellido)
                    putExtra("formaDePago", formaDePago)
                    putExtra("monto", monto)
                    putExtra("observaciones", "Pago de cuota mensual del gimnasio")
                    putExtra("fechaPago", getCurrentDate())
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

}