package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MembershipPay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_membership_pay)

        val logoButton: ImageView = findViewById(R.id.logo)

        logoButton.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }

        val pagoCuotaMenualButton: Button = findViewById(R.id.btn_pago_cuota_mensual)

        pagoCuotaMenualButton.setOnClickListener {
            val intent = Intent(this, CollectMonthlyFee::class.java)
            startActivity(intent)
        }

        val listadoMorososButton: Button = findViewById(R.id.btn_listado_morosos)

        listadoMorososButton.setOnClickListener {
            val intent = Intent(this, MorososActivity::class.java)
            startActivity(intent)
        }

        val atrasButton: Button = findViewById(R.id.btn_atras)

        atrasButton.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }

    }
}