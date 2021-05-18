package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val save_bt : Button = findViewById(R.id.save_bt)
        val clear_bt : Button = findViewById(R.id.clear_bt)
        val home_bt : Button = findViewById(R.id.home_bt)

        save_bt.setOnClickListener {

        }
        clear_bt.setOnClickListener {

        }
        home_bt.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

    override fun onBackPressed() {
        Toast.makeText(this, "Please press home button to exit", Toast.LENGTH_SHORT).show()
    }
}