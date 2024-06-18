package com.example.platzi

import DatabaseHelper
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val dbHelper = DatabaseHelper(this)
        val userDataString = dbHelper.getAllUsersString()

        val textView: TextView = findViewById(R.id.textView)
        textView.text = userDataString
    }
}
