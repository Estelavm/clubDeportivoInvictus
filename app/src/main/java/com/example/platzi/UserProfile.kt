package com.example.platzi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class UserProfileActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        db = DatabaseHelper.getInstance(this)

        val editTextDNI: EditText = findViewById(R.id.editTextDNI)
        val buttonSearch: Button = findViewById(R.id.buttonSearch)
        val textViewUserData: TextView = findViewById(R.id.textViewUserData)
        val buttonEdit: Button = findViewById(R.id.buttonEdit)
        val buttonDelete: Button = findViewById(R.id.buttonDelete)

        buttonSearch.setOnClickListener {
            val dni = editTextDNI.text.toString()
            if (dni.isNotEmpty()) {
                val user = db.getUserByDNI(dni)
                if (user != null) {
                    textViewUserData.text = "Nombre: ${user.name} ${user.lastName}\nTipo y N° de Documento: ${user.docType} ${user.docNumber}\nTipo de Usuario: ${user.userType}"
                    textViewUserData.visibility = View.VISIBLE
                    buttonEdit.visibility = View.VISIBLE
                    buttonDelete.visibility = View.VISIBLE

                    buttonEdit.setOnClickListener {
                        val intent = Intent(this, EditUserActivity::class.java)
                        intent.putExtra("user", user)
                        startActivity(intent)
                    }

                    buttonDelete.setOnClickListener {
                        val result = db.deleteUserByDNI(dni)
                        if (result > 0) {
                            textViewUserData.visibility = View.GONE
                            buttonEdit.visibility = View.GONE
                            buttonDelete.visibility = View.GONE
                            Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show()
                        }
                    }
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

        val atrasButton: Button = findViewById(R.id.btn_atras)
        atrasButton.setOnClickListener {
            val intent = Intent(this, BPrincipalActivity::class.java)
            startActivity(intent)
        }
    }
}
