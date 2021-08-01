package com.example.stickynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val itemsList = ArrayList<String>()
    private lateinit var RecyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addBt : FloatingActionButton = findViewById(R.id.addBt)
        addBt.setOnClickListener { startActivity(Intent(this, AddNewNotes::class.java)) }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        RecyclerAdapter = RecyclerAdapter(itemsList)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RecyclerAdapter
        prepareItems()
    }
    private fun prepareItems() {
        val databaseHelper = DatabaseHelper(this)
        val data = databaseHelper.read()
        if (data.count>0) {
            while (data.moveToNext()) {
                itemsList.add(data.getString(1))
                //RecyclerAdapter .notifyDataSetChanged()
            }

            Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show()
        }

        //itemsList.add("Item 1")
        //itemsList.add("Item 2")
        //itemsList.add("Item 3")
        //itemsList.add("Item 4")
        //itemsList.add("Item 5")
        //itemsList.add("Item 6")
        //itemsList.add("Item 7")
        //itemsList.add("Item 8")
        //itemsList.add("Item 9")
        //itemsList.add("Item 10")
        //itemsList.add("Item 11")
        //itemsList.add("Item 12")
        //itemsList.add("Item 13")
        RecyclerAdapter .notifyDataSetChanged()
    }

}