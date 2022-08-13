package com.example.mvcmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val textUser : TextView = findViewById(R.id.text_user)
        val logoutButton : Button = findViewById(R.id.logout_button)

        val message = intent.getStringExtra("message")
        textUser.text = message

        logoutButton.setOnClickListener {
            Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}