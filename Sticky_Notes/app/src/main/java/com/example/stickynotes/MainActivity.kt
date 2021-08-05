package com.example.stickynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val itemsList = ArrayList<NotesList>()
    private lateinit var recyclerAdapter : RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addBt : FloatingActionButton = findViewById(R.id.addBt)
        addBt.setOnClickListener { startActivity(Intent(this, AddNewNotes::class.java)) }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerAdapter = RecyclerAdapter(itemsList,this)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = recyclerAdapter
        prepareItems()
    }
    private fun prepareItems() {
        val databaseHelper = DatabaseHelper(this)
        val data = databaseHelper.read()
        if (data.count>0) {
            while (data.moveToNext()) {
                val id = data.getString(0)
                val title = data.getString(0)
                val note = data.getString(0)
                val notesList = NotesList(id, title, note)
                itemsList.add(notesList)
            }
        }
        Toast.makeText(this, "Data Retrieved", Toast.LENGTH_SHORT).show()
        recyclerAdapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        finishAffinity()
        //super.onBackPressed()
    }
}