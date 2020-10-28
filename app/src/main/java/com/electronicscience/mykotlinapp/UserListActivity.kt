package com.electronicscience.mykotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.electronicscience.mykotlinapp.database.UserDatabase
import com.electronicscience.mykotlinapp.databinding.ActivityUserListBinding

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        binding.recycler.layoutManager = LinearLayoutManager(this)

        val adapter = UserListAdapter { user ->
            Toast.makeText(this, "ID: ${user.id}", Toast.LENGTH_SHORT).show()
        }
        binding.recycler.adapter = adapter


        val liveUsers = UserDatabase(this).userDao().getAllUsersLiveData()

        liveUsers.observe(this, Observer {
            if (it != null) {
                adapter.users = it
                adapter.notifyDataSetChanged()
            }
        })
    }
}