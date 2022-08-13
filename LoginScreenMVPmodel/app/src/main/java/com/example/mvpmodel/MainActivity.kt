package com.example.mvpmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mvpmodel.presenter.Presenter
import com.example.mvpmodel.view.IView

class MainActivity : AppCompatActivity() , IView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userEmail : EditText = findViewById(R.id.user_email)
        val userPassword : EditText = findViewById(R.id.user_password)
        val loginButton : Button = findViewById(R.id.login_button)
        val loginPresenter = Presenter(this)

        loginButton.setOnClickListener {
            loginPresenter.onLogin(
                userEmail.text.toString(),
                userPassword.text.toString().trim()
            )
        }

    }

    override fun onLoginSuccess(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        startActivity(Intent(this, MainActivity2::class.java).putExtra("message" , message))
    }

    override fun onLoginError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}