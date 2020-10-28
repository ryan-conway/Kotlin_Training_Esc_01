package com.electronicscience.mykotlinapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.electronicscience.mykotlinapp.database.User

class UserListAdapter(private val listener: (User) -> Unit): RecyclerView.Adapter<UserViewHolder>() {

    var users: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        holder.tvUsername.text = user.username
        holder.tvPassword.text = user.password

        holder.itemView.setOnClickListener {
            listener.invoke(user)
        }
    }
}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvUsername: TextView = itemView.findViewById(R.id.tvUsername)
    val tvPassword: TextView = itemView.findViewById(R.id.tvPassword)
}