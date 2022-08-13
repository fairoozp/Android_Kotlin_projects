package com.example.headerfooter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = ArrayList<List>()
        for (i in 1..50){
            when(i){
                1,11,21,31,41 -> {
                    data.add(List("Numbers From $i to ${i+9}",2))
                    data.add(List("Number : $i",1))
                }
                else -> data.add(List("Number : $i" , 1))
            }
        }
        data.add(List("Finished",3))

        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(data)

    }
}