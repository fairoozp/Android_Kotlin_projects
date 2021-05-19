package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val read : Button = findViewById(R.id.read)
    private val add : FloatingActionButton = findViewById(R.id.add)
    private val view : TextView = findViewById(R.id.view)
    private val databaseHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add.setOnClickListener {
            startActivity( Intent(this, MainActivity2::class.java))
        }
        read.setOnClickListener {
            val data = databaseHelper.readData()
            val stringBuffer = StringBuffer()
            if (data.count>0){
                while (data.moveToNext()){
                    stringBuffer.append("ID : ${data.getString(0)}\n")
                    stringBuffer.append("NAME : ${data.getString(1)}\n")
                    stringBuffer.append("PHONE NUMBER : ${data.getString(2)}\n")
                    stringBuffer.append("EMAIL : ${data.getString(3)}\n")
                    stringBuffer.append("ADDRESS : ${data.getString(4)}\n")
                }
                view.text = stringBuffer.toString()
                Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onBackPressed() {
        finishAffinity()
    }
}