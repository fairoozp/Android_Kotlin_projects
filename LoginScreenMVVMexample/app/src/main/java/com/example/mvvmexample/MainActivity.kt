package com.example.mvvmexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmexample.model.User
import com.example.mvvmexample.viewModel.ViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : ViewModel
    private lateinit var loginButton : Button
    private lateinit var userEmail : EditText
    private lateinit var userPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById(R.id.login_button)
        userEmail = findViewById(R.id.user_email)
        userPassword = findViewById(R.id.user_password)

        viewModel = ViewModelProvider(this)[ViewModel::class.java]

        viewModel.currentEmail.observe(this, {
            userEmail.setText(it)
        })
        viewModel.currentPassword.observe(this, {
            userPassword.setText(it)
        })

        loginButton.setOnClickListener {
            viewModel.email = userEmail.text.toString()
            viewModel.password = userPassword.text.toString()
            onLogin(viewModel.email,viewModel.password)
        }

        liveData()

    }

    private fun liveData(){

        viewModel.currentEmail.value = viewModel.email
        viewModel.currentPassword.value = viewModel.password

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