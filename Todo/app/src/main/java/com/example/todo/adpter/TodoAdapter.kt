package com.example.todo.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.model.TodoModel

class TodoAdapter (private val data : ArrayList<TodoModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val checkBox : CheckBox = view.findViewById(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_item,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = data[position]
        (holder as ViewHolder).checkBox.text = model.todo
    }

    override fun getItemCount(): Int {
        return data.size
    }

}