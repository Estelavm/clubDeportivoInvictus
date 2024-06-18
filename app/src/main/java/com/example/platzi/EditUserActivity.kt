package com.example.platzi

import DatabaseHelper
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.platzi.model.User

class EditUserActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var docNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        dbHelper = DatabaseHelper(this)

        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextLastName: EditText = findViewById(R.id.editTextLastName)
        val editTextDocType: EditText = findViewById(R.id.editTextDocType)
        val editTextDocNumber: EditText = findViewById(R.id.editTextDocNumber)
        val editTextUserType: EditText = findViewById(R.id.editTextUserType)
        val buttonSave: Button = findViewById(R.id.buttonSave)

        val user: User? = intent.getParcelableExtra("user")
        if (user != null) {
            editTextName.setText(user.name)
            editTextLastName.setText(user.lastName)
            editTextDocType.setText(user.docType)
            editTextDocNumber.setText(user.docNumber)
            editTextUserType.setText(user.userType)
            docNumber = user.docNumber
        }

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val lastName = editTextLastName.text.toString()
            val docType = editTextDocType.text.toString()
            val docNumber = editTextDocNumber.text.toString()
            val userType = editTextUserType.text.toString()

            val result = dbHelper.updateUserData(docNumber, name, lastName, docType, userType)
            if (result > 0) {
                Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al actualizar usuario", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
