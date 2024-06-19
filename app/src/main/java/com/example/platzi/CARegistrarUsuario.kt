package com.example.platzi

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.imageview.ShapeableImageView

class CARegistrarUsuario : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_caregistrar_usuario)
        databaseHelper = DatabaseHelper.getInstance(this)

        val logoImageView: ShapeableImageView = findViewById(R.id.logo)
        logoImageView.setOnClickListener {
            startActivity(Intent(this, BPrincipalActivity::class.java))
        }

        val atrasButton = findViewById<Button>(R.id.btn_atras)
        atrasButton.setOnClickListener {
            startActivity(Intent(this, BPrincipalActivity::class.java))
        }

        val ingresarButton = findViewById<Button>(R.id.btn_ingresar)
        ingresarButton.setOnClickListener {
            val name = findViewById<EditText>(R.id.editText_nombre).text.toString().trim()
            val lastName = findViewById<EditText>(R.id.editText2).text.toString().trim()
            val docType = findViewById<Spinner>(R.id.spinner_tipo).selectedItem.toString()
            val docNumber = findViewById<EditText>(R.id.editText3).text.toString().trim()
            val isSocio = findViewById<RadioButton>(R.id.radio_socio).isChecked

            if (name.isNotEmpty() && lastName.isNotEmpty() && docType.isNotEmpty() && docNumber.isNotEmpty()) {
                val id = databaseHelper.insertUserData(name, lastName, docType, docNumber, if (isSocio) "Socio" else "No Socio")
                if (id != -1L) {
                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()

                    val intent = if (isSocio) {
                        Intent(this, DAPagoSocio::class.java)
                    } else {
                        Intent(this, FARegistroDeActividad::class.java)
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error registering user", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter all the data", Toast.LENGTH_SHORT).show()
            }
        }

        val spinner = findViewById<Spinner>(R.id.spinner_tipo)
        ArrayAdapter.createFromResource(
            this,
            R.array.tipos_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onDestroy() {
        databaseHelper.close()
        super.onDestroy()
    }
}
