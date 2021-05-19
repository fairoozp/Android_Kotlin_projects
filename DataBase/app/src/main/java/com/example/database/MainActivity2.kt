package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    val name : EditText = findViewById(R.id.name)
    val phone :EditText = findViewById(R.id.phone)
    val email : EditText = findViewById(R.id.email)
    val address : EditText = findViewById(R.id.address)
    val save_bt : Button = findViewById(R.id.save_bt)
    val clear_bt : Button = findViewById(R.id.clear_bt)
    val home_bt : Button = findViewById(R.id.home_bt)
    val databaseHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        save_bt.setOnClickListener {
            insert_data()
            clear_all()
        }
        clear_bt.setOnClickListener {
            clear_all()
        }
        home_bt.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun insert_data() {
        val name = name.text.toString()
        val phone = phone.text.toString()
        val email = email.text.toString()
        val address = address.text.toString()
        val result : Boolean = databaseHelper.insertData(name,phone,email,address)
        when{
            result -> Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clear_all() {
        name.text.clear()
        phone.text.clear()
        email.text.clear()
        address.text.clear()
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Please press home button to exit", Toast.LENGTH_SHORT).show()
    }
}