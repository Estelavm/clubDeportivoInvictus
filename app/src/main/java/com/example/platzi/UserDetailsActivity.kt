package com.example.platzi

import DatabaseHelper
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        db = DatabaseHelper.getInstance(this)
        val userDataString = db.getAllUsersString()

        val textView: TextView = findViewById(R.id.textView)
        textView.text = userDataString
    }
}
