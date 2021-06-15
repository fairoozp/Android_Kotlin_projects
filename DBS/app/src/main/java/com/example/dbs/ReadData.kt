package com.example.dbs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class ReadData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_data)
        
        val download :ImageButton = findViewById(R.id.download)
        download.setOnClickListener { Toast.makeText(this, "Wait For This Feature", Toast.LENGTH_SHORT).show() }

        val databaseHelper = DatabaseHelper(this)
        val data = databaseHelper.readData()
        val view : TextView = findViewById(R.id.view)
        val stringBuffer = StringBuffer()
        if (data.count>0){
            while (data.moveToNext()){
                stringBuffer.append(data.getString(1))
                stringBuffer.append("   DATE : ${data.getString(2)}\n")
                stringBuffer.append("TDD : ${data.getString(3)}    ")
                stringBuffer.append("Post Food : ${data.getString(4)}    ")
                stringBuffer.append("Food Carb : ${data.getString(5)}\n")
                stringBuffer.append("ISF : ${data.getString(6)}")
                stringBuffer.append("   ICR : ${data.getString(7)}")
                stringBuffer.append("   CD : ${data.getString(8)}\n------------------------------------------------\n")

            }
            view.text = stringBuffer.toString()
            Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
        }

    }
}