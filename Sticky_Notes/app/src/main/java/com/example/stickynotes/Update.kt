package com.example.stickynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val databaseHelper = DatabaseHelper(this)

        val saveBt : Button = findViewById(R.id.saveBt)
        val clearBt : Button = findViewById(R.id.clearBt)
        val homeBt : Button = findViewById(R.id.homeBt)
        val delete : Button = findViewById(R.id.delete)
        val title1 : EditText = findViewById(R.id.title)
        val notes1 : EditText = findViewById(R.id.notes)

        val id = intent.getStringExtra("id").toString()
        val title2 = intent.getStringExtra("title").toString()
        val notes2 = intent.getStringExtra("notes").toString()
        title1.setText(title2)
        notes1.setText(notes2)

        clearBt.setOnClickListener { notes1.text.clear() }
        homeBt.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }


        saveBt.setOnClickListener {
            if (notes1.text.isNotEmpty()){
                val title = title1.text.toString()
                val notes = notes1.text.toString()
                val result : Boolean = databaseHelper.update(id, title, notes)
                when{
                    result -> Toast.makeText(applicationContext, "Data Updated", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
            notes1.text.clear()
            startActivity(Intent(this, MainActivity::class.java))
        }


        delete.setOnClickListener {
            val result : Boolean = databaseHelper.delete(id)
            when{
                result -> Toast.makeText(applicationContext, "Data Deleted", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}