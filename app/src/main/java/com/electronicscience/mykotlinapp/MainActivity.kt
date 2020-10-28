package com.electronicscience.mykotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.electronicscience.mykotlinapp.database.UserDatabase
import com.electronicscience.mykotlinapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.button.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            val user = UserDatabase.getInstance(this).userDao().getUser(username)
            if (user == null) {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            } else if (user.username == username && user.password == password) {
                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}