package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.platzi.model.User

class EditUserActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    private lateinit var docNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        db = DatabaseHelper.getInstance(this)

        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextLastName: EditText = findViewById(R.id.editTextLastName)
        val spinnerDocType: Spinner = findViewById(R.id.editTextDocType)
        val editTextDocNumber: EditText = findViewById(R.id.editTextDocNumber)
        val radioGroupUserType: RadioGroup = findViewById(R.id.radioGroupUserType)
        val buttonSave: Button = findViewById(R.id.buttonSave)

        ArrayAdapter.createFromResource(
            this,
            R.array.tipos_array,
            R.layout.spinner_items
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_items)
            spinnerDocType.adapter = adapter
        }

        val user: User? = intent.getParcelableExtra("user")
        if (user != null) {
            editTextName.setText(user.name)
            editTextLastName.setText(user.lastName)
            // Set the spinner to the correct value
            val docTypePosition = resources.getStringArray(R.array.tipos_array).indexOf(user.docType)
            spinnerDocType.setSelection(docTypePosition)
            editTextDocNumber.setText(user.docNumber)
            // Set the radio button to the correct value
            when (user.userType) {
                "Socio" -> radioGroupUserType.check(R.id.radio_socio)
                "No Socio" -> radioGroupUserType.check(R.id.radio_no_socio)
            }
            docNumber = user.docNumber
        }

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val lastName = editTextLastName.text.toString()
            val docType = spinnerDocType.selectedItem.toString()
            val docNumber = editTextDocNumber.text.toString()
            val userType = when (radioGroupUserType.checkedRadioButtonId) {
                R.id.radio_socio -> "Socio"
                R.id.radio_no_socio -> "No Socio"
                else -> ""
            }

            if (name.isEmpty() || lastName.isEmpty() || docType.isEmpty() || docNumber.isEmpty() || userType.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val result = db.updateUserData(docNumber, name, lastName, docType, userType)
                if (result > 0) {
                    Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al actualizar usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }


        val atrasButton: Button = findViewById(R.id.btn_atras)
        atrasButton.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
