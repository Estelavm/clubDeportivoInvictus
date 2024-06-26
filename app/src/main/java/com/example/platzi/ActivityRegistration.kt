package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActivityRegistration : AppCompatActivity() {

    private lateinit var basketCheckBox: CheckBox
    private lateinit var futbolCheckBox: CheckBox
    private lateinit var natacionCheckBox: CheckBox
    private lateinit var tenisCheckBox: CheckBox
    private lateinit var voleyCheckBox: CheckBox
    private lateinit var dniEditText: EditText
    private lateinit var totalToPayTextView: TextView
    private lateinit var formaDePagoEfectivoRadioButton: RadioButton
    private lateinit var formaDePagoTarjetaRadioButton: RadioButton

    private var totalToPay: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val logoButton: ImageView = findViewById(R.id.logo)

        logoButton.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }

        val atrasButton: Button = findViewById(R.id.btn_atras)

        atrasButton.setOnClickListener {
            val intent = Intent(this, PrincipalActivity::class.java)
            startActivity(intent)
        }

        // Inicializar vistas
        basketCheckBox = findViewById(R.id.check_basket)
        futbolCheckBox = findViewById(R.id.check_futbol)
        natacionCheckBox = findViewById(R.id.check_natacion)
        tenisCheckBox = findViewById(R.id.check_tenis)
        voleyCheckBox = findViewById(R.id.check_voley)
        dniEditText = findViewById(R.id.editText1)
        totalToPayTextView = findViewById(R.id.total_to_pay)
        formaDePagoEfectivoRadioButton = findViewById(R.id.radio_efectivo)
        formaDePagoTarjetaRadioButton = findViewById(R.id.radio_tarjeta)


        // Manejar clics en el botón PAGAR
        val pagarButton: Button = findViewById(R.id.btn_imprimir)
        pagarButton.setOnClickListener {
            val dni = dniEditText.text.toString().trim()

            if (dni.isNotEmpty()) {
                val actividadesSeleccionadas = obtenerActividadesSeleccionadas()
                val formaDePago = if (formaDePagoEfectivoRadioButton.isChecked) "Efectivo" else "Tarjeta"
                val intent = Intent(this, ActivityPaymentReceipt::class.java).apply {
                    putExtra("dni", dni)
                    putExtra("actividades", actividadesSeleccionadas)
                    putExtra("formaDePago", formaDePago)
                    putExtra("monto", totalToPay)
                }
                startActivity(intent)

            } else {
                Toast.makeText(this, "Ingrese un DNI válido", Toast.LENGTH_SHORT).show()
            }
        }

        // Manejar cambios en los CheckBoxes y calcular monto total
        setupCheckBoxListeners()
    }

    private fun setupCheckBoxListeners() {
        basketCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                totalToPay += 2000.00
            } else {
                totalToPay -= 2000.00
            }
            updateTotalToPay()
        }

        futbolCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                totalToPay += 2000.00
            } else {
                totalToPay -= 2000.00
            }
            updateTotalToPay()
        }

        natacionCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                totalToPay += 2000.00
            } else {
                totalToPay -= 2000.00
            }
            updateTotalToPay()
        }

        tenisCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                totalToPay += 2000.00
            } else {
                totalToPay -= 2000.00
            }
            updateTotalToPay()
        }

        voleyCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                totalToPay += 2000.00
            } else {
                totalToPay -= 2000.00
            }
            updateTotalToPay()
        }
    }
    private fun updateTotalToPay() {
        totalToPayTextView.text = getString(R.string.total_to_pay, totalToPay)
    }

    private fun obtenerActividadesSeleccionadas(): String {
        val actividades = mutableListOf<String>()
        if (basketCheckBox.isChecked) actividades.add("BASQUET")
        if (futbolCheckBox.isChecked) actividades.add("FUTBOL")
        if (natacionCheckBox.isChecked) actividades.add("NATACION")
        if (tenisCheckBox.isChecked) actividades.add("TENIS")
        if (voleyCheckBox.isChecked) actividades.add("VOLEY")
        return actividades.joinToString(", ")
    }

}
