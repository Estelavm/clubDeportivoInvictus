package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MembershipPaymentReceipt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_membership_payment_receipt)

        val logoButton: ImageView = findViewById(R.id.logo)

        logoButton.setOnClickListener {
            val intent = Intent(this, BPrincipalActivity::class.java)
            startActivity(intent)
        }

        val nombreApellido = intent.getStringExtra("nombreApellido")
        val fechaPago = intent.getStringExtra("fechaPago")
        val formaDePago = intent.getStringExtra("formaDePago")
        val monto = intent.getDoubleExtra("monto", 0.0)
        val observaciones = intent.getStringExtra("observaciones")

        val txtNombreApellido: TextView = findViewById(R.id.txtNombreApellido)
        val txtFechaPago: TextView = findViewById(R.id.txtFechaPago)
        val txtFormaDePago: TextView = findViewById(R.id.txtFormaDePago)
        val txtMonto: TextView = findViewById(R.id.txtMonto)
        val txtObservaciones: TextView = findViewById(R.id.txtObservaciones)

        txtNombreApellido.text = nombreApellido
        txtFechaPago.text = fechaPago
        txtFormaDePago.text = formaDePago
        txtMonto.text = monto.toString()
        txtObservaciones.text = observaciones

        val atrasButton: Button = findViewById(R.id.btn_atras)

        atrasButton.setOnClickListener {
            val intent = Intent(this, DBPagoCuotaMensual::class.java)
            startActivity(intent)
        }
    }
}