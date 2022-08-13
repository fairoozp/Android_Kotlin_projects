package com.example.loadingwiththreshold

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter (private val data : ArrayList<Data>, val listener: (Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val textView : TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = data[position]
        (holder as ViewHolder).textView.text = model.item
        holder.textView.setOnClickListener {
            listener(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}