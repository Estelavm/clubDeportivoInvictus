package com.example.platzi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.platzi.R
import com.example.platzi.model.User

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)
        val docTypeTextView: TextView = itemView.findViewById(R.id.docTypeTextView)
        val docNumberTextView: TextView = itemView.findViewById(R.id.docNumberTextView)
        val userTypeTextView: TextView = itemView.findViewById(R.id.userTypeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.nameTextView.text = user.name
        holder.lastNameTextView.text = user.lastName
        holder.docTypeTextView.text = user.docType
        holder.docNumberTextView.text = user.docNumber
        holder.userTypeTextView.text = user.userType
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
