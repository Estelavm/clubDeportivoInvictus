package com.example.platzi

import DatabaseHelper
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserProfileActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        dbHelper = DatabaseHelper(this)

        val editTextDNI: EditText = findViewById(R.id.editTextDNI)
        val buttonSearch: Button = findViewById(R.id.buttonSearch)
        val textViewUserData: TextView = findViewById(R.id.textViewUserData)
        val buttonEdit: Button = findViewById(R.id.buttonEdit)
        val buttonDelete: Button = findViewById(R.id.buttonDelete)

        buttonSearch.setOnClickListener {
            val dni = editTextDNI.text.toString()
            if (dni.isNotEmpty()) {
                val user = dbHelper.getUserByDNI(dni)
                if (user != null) {
                    textViewUserData.text = "Name: ${user.name} ${user.lastName}\nDocument: ${user.docType} ${user.docNumber}\nType: ${user.userType}"
                    textViewUserData.visibility = View.VISIBLE
                    buttonEdit.visibility = View.VISIBLE
                    buttonDelete.visibility = View.VISIBLE
                } else {
                    textViewUserData.visibility = View.GONE
                    buttonEdit.visibility = View.GONE
                    buttonDelete.visibility = View.GONE
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Ingrese un DNI", Toast.LENGTH_SHORT).show()
            }
        }

        buttonEdit.setOnClickListener {
            // Implementar lógica para editar usuario
        }

        buttonDelete.setOnClickListener {
            val dni = editTextDNI.text.toString()
            if (dni.isNotEmpty()) {
                val result = dbHelper.deleteUserByDNI(dni)
                if (result > 0) {
                    textViewUserData.visibility = View.GONE
                    buttonEdit.visibility = View.GONE
                    buttonDelete.visibility = View.GONE
                    Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
