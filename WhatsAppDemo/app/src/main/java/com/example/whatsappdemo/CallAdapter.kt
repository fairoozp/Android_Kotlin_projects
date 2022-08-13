package com.example.whatsappdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CallAdapter (private val data : ArrayList<StatusData> , val context: Context) : RecyclerView.Adapter<CallAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val username : TextView = view.findViewById(R.id.username_call)
        val call : ImageButton = view.findViewById(R.id.call_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.calls_item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = data[position]
        holder.username.text = model.name
        holder.call.setOnClickListener {
            Toast.makeText(context, "Calling ${model.name}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}