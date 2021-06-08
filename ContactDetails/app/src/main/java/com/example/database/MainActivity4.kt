package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        
        val value : EditText = findViewById(R.id.value)
        val cancel : Button = findViewById(R.id.cancel)
        val delete : Button = findViewById(R.id.delete)
        val databaseHelper = DatabaseHelper(this)
        
        cancel.setOnClickListener { 
            startActivity(Intent(this, MainActivity::class.java))
        }
        delete.setOnClickListener { 
            val strld = value.text.toString()
            val result : Boolean = databaseHelper.deleteData(strld)
            
            when{
                result -> Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onBackPressed() {
        Toast.makeText(this, "Please press home button to exit", Toast.LENGTH_SHORT).show()
    }
}