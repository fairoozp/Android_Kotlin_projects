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
        RecyclerAdapter = RecyclerAdapter(itemsList,this)
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
            }

            Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show()
        }

        RecyclerAdapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        finishAffinity()
        //super.onBackPressed()
    }
}