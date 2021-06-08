package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name1 : EditText = findViewById(R.id.name)
        val phone1 :EditText = findViewById(R.id.phone)
        val email1 : EditText = findViewById(R.id.email)
        val address1 : EditText = findViewById(R.id.address)
        val saveBt : Button = findViewById(R.id.save_bt)
        val clearBt : Button = findViewById(R.id.clear_bt)
        val homeBt : Button = findViewById(R.id.home_bt)
        val databaseHelper = DatabaseHelper(this)

        saveBt.setOnClickListener {
            val name = name1.text.toString()
            val phone = phone1.text.toString()
            val email = email1.text.toString()
            val address = address1.text.toString()
            val result : Boolean = databaseHelper.insertData(name,phone,email,address)
            when{
                result -> Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
        clearBt.setOnClickListener {
            name1.text.clear()
            phone1.text.clear()
            email1.text.clear()
            address1.text.clear()
        }
        homeBt.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
    override fun onBackPressed() {
        Toast.makeText(this, "Please press home button to exit", Toast.LENGTH_SHORT).show()
    }
}