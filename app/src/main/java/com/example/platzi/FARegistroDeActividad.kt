package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class FARegistroDeActividad : AppCompatActivity() {

    private lateinit var basketCheckBox: CheckBox
    private lateinit var futbolCheckBox: CheckBox
    private lateinit var natacionCheckBox: CheckBox
    private lateinit var tenisCheckBox: CheckBox
    private lateinit var voleyCheckBox: CheckBox
    private lateinit var dniEditText: EditText
    private lateinit var totalToPayTextView: TextView

    private var totalToPay: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faregistro_de_actividad)

        val logoButton: ImageView = findViewById(R.id.logo)

        logoButton.setOnClickListener {
            val intent = Intent(this, BPrincipalActivity::class.java)
            startActivity(intent)
        }

        val atrasButton: Button = findViewById(R.id.btn_atras)

        atrasButton.setOnClickListener {
            val intent = Intent(this, BPrincipalActivity::class.java)
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

        // Manejar clics en el botón PAGAR
        val pagarButton: Button = findViewById(R.id.btn_imprimir)
        pagarButton.setOnClickListener {
            val dni = dniEditText.text.toString().trim()

            // Verificar que se haya ingresado un DNI válido
            if (dni.isNotEmpty()) {
                val intent = Intent(this, FBPagoActividad::class.java)
                intent.putExtra("dni", dni)
                startActivity(intent)
            } else {
                // Mostrar mensaje de error o realizar alguna acción según tu flujo
                // por ejemplo: Toast.makeText(this, "Ingrese un DNI válido", Toast.LENGTH_SHORT).show()
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

}
