package com.example.stickynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val databaseHelper = DatabaseHelper(this)

        val saveBt : Button = findViewById(R.id.saveBt)
        val clearBt : Button = findViewById(R.id.clearBt)
        val homeBt : Button = findViewById(R.id.homeBt)
        val delete : Button = findViewById(R.id.delete)
        val notes : EditText = findViewById(R.id.notes)

        val note = intent.getStringExtra("notes")
        notes.setText(note)
        //var data = databaseHelper.select(note.toString())
        //Toast.makeText(this, "hello $data", Toast.LENGTH_SHORT).show()

        clearBt.setOnClickListener { notes.text.clear() }
        homeBt.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }


        saveBt.setOnClickListener {
            if (notes.text.isNotEmpty()){
                val notes = notes.text.toString()
                val result : Boolean = databaseHelper.update(note.toString(),notes)
                when{
                    result -> Toast.makeText(applicationContext, "Data Updated", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
            notes.text.clear()
            startActivity(Intent(this, MainActivity::class.java))
        }


        delete.setOnClickListener {
            val note = notes.text.toString()
            val result : Boolean = databaseHelper.delete(note)
            when{
                result -> Toast.makeText(applicationContext, "Data Deleted", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Press Home to exit", Toast.LENGTH_SHORT).show()
        //
    }
}