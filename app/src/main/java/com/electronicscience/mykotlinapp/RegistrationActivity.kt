package com.electronicscience.mykotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.electronicscience.mykotlinapp.database.User
import com.electronicscience.mykotlinapp.database.UserDatabase
import com.electronicscience.mykotlinapp.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration)

        binding.bRegister.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            when {
                username.isBlank() -> toast("username required")
                password.isBlank() -> toast("password is required")
                password != confirmPassword -> toast("passwords do not match")
                else -> {
                    val user = User(username = username, password = password)
                    val id = UserDatabase.getInstance(this).userDao().insert(user)
                    finish()
                }
            }
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}