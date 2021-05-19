package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val read : Button = findViewById(R.id.read)
        val add : FloatingActionButton = findViewById(R.id.add)
        val view : TextView = findViewById(R.id.view)
        val remove : FloatingActionButton = findViewById(R.id.remove)
        val update : Button = findViewById(R.id.update)
        val delete : Button = findViewById(R.id.delete)
        val databaseHelper = DatabaseHelper(this)

        add.setOnClickListener {
            startActivity( Intent(this, MainActivity2::class.java))
        }
        read.setOnClickListener {
            val data = databaseHelper.readData()
            val stringBuffer = StringBuffer()
            if (data.count>0){
                while (data.moveToNext()){
                    stringBuffer.append("ID  :  ${data.getString(0)}\n\n")
                    stringBuffer.append("NAME  :  ${data.getString(1)}\n")
                    stringBuffer.append("PHONE NUMBER  :  ${data.getString(2)}\n")
                    stringBuffer.append("EMAIL  :  ${data.getString(3)}\n\n")
                    stringBuffer.append("ADDRESS \n\n ${data.getString(4)}\n\n----------------------------------------\n\n")
                }
                view.text = stringBuffer.toString()
                Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
            }
        }
        remove.setOnClickListener {
            databaseHelper.deleteAll()
            view.text = ""
            Toast.makeText(this, "All Data Removed", Toast.LENGTH_SHORT).show()
        }
        delete.setOnClickListener {
            startActivity(Intent(this,MainActivity4::class.java))
        }
        update.setOnClickListener {
            startActivity(Intent(this,MainActivity3::class.java))
        }
    }
    override fun onBackPressed() {
        finishAffinity()
    }
}