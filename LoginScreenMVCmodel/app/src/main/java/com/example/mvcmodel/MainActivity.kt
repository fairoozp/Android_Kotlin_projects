package com.example.mvcmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mvcmodel.model.User

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userEmail : EditText = findViewById(R.id.user_email)
        val userPassword : EditText = findViewById(R.id.user_password)
        val loginButton : Button = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            val email = userEmail.text.toString()
            val password = userPassword.text.toString()
            onLogin(email,password)
        }

    }

    private fun onLogin(email: String, password: String) {
        val user = User(email,password)
        when(user.checkValid()){
            0 -> {
                onLoginError("Please enter Email address")
            }
            1 -> {
                onLoginError("Please enter a valid Email address")
            }
            2 -> {
                onLoginError("Please enter the password")
            }
            3 -> {
                onLoginError("Please enter a strong password\nHint : Enter more than 6 character")
            }
            4 -> {
                onLoginError("User does not exist, Please try again")
            }
            5 -> {
                onLoginError("Wrong password, check password and try again")
            }
            else -> {
                onLoginSuccess()
            }
        }
    }

    private fun onLoginSuccess() {
        val message = "Login Successful\nWelcome Fairooz"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        startActivity(Intent(this, MainActivity2::class.java).putExtra("message" , message))
    }

    private fun onLoginError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}