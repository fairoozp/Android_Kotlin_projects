package com.example.whatsappdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewChat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_chat)

        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.up_home_arrow)
        }

        val data = ArrayList<ChatItems>()
        for (i in 1..50){
            data.add(ChatItems("User $i", "Message $i", "10:$i"))
        }

        val recyclerview : RecyclerView = findViewById(R.id.newChat)
        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        val adapter = RecyclerAdapterChat(data,this)
        recyclerview.adapter = adapter

    }
}