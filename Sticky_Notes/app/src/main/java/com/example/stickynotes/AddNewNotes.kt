package com.example.stickynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddNewNotes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_notes)

        val databaseHelper = DatabaseHelper(this)

        val saveBt : Button = findViewById(R.id.saveBt)
        val clearBt : Button = findViewById(R.id.clearBt)
        val homeBt : Button = findViewById(R.id.homeBt)
        val title1 : EditText = findViewById(R.id.title)
        val notes1 : EditText = findViewById(R.id.notes)

        clearBt.setOnClickListener { notes1.text.clear() }
        homeBt.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }
        saveBt.setOnClickListener {
            if (notes1.text.isNotEmpty()){
                val title = title1.text.toString()
                val notes = notes1.text.toString()
                val result : Boolean = databaseHelper.insert(title,notes)
                when{
                    result -> Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
            notes1.text.clear()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}