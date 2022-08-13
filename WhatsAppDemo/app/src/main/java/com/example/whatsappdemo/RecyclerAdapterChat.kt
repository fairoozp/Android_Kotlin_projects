package com.example.whatsappdemo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterChat (private val data : ArrayList<ChatItems>, private val context : Context) :RecyclerView.Adapter<RecyclerAdapterChat.ViewHolder>() {

    class ViewHolder(chat_view : View) : RecyclerView.ViewHolder(chat_view){
        var name : TextView = chat_view.findViewById(R.id.name)
        var message : TextView = chat_view.findViewById(R.id.message)
        var time : TextView = chat_view.findViewById(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = data[position]
        holder.name.text = model.username
        holder.message.text = model.userMessage
        holder.time.text = model.timeMessage
        holder.itemView.setOnClickListener {
            Toast.makeText(context, model.username, Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context,ChatActivity::class.java).putExtra("name",model.username))
        }
    }

    override fun getItemCount(): Int {
        return  data.size
    }
}