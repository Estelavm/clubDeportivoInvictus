package com.example.platzi

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.platzi.adapter.UserAdapter

class UserListActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        db = DatabaseHelper.getInstance(this)
        val userList = db.getAllUsersList()

        val adapter = UserAdapter(userList)
        recyclerView.adapter = adapter

        val button: Button = findViewById(R.id.viewDetailsButton)
        button.setOnClickListener {
            val intent = Intent(this, UserDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
