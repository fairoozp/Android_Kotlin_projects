package com.example.todo.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.adpter.TodoAdapter
import com.example.todo.model.TodoModel
import com.example.todo.viewModel.TodoViewModel

class MainActivity : AppCompatActivity() {

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = ArrayList<TodoModel>()

        val todoViewModel : TodoViewModel =ViewModelProvider(this)[TodoViewModel::class.java]
        todoViewModel.currentData.observe(this, {
            data.addAll(it)
        })

        val button : Button = findViewById(R.id.button)
        val editText : EditText = findViewById(R.id.editText)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(this)
        val adapter = TodoAdapter(data)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        button.setOnClickListener {
            data.add(TodoModel(editText.text.toString()))
            todoViewModel.data.add(TodoModel(editText.text.toString()))
            adapter.notifyDataSetChanged()
            recyclerView.scrollToPosition(data.size -1)
            editText.text.clear()
        }

    }
}