package com.example.stickynotes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
//import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private var itemsList: ArrayList<NotesList>, var context: Context) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title : TextView = view.findViewById(R.id.title)
        var note : TextView = view.findViewById(R.id.note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.view, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList[position]
        //val id = itemsList[getItemId(position).toInt()]
        holder.title.text = item.getTitle()
        holder.note.text = item.getNote()
        holder.itemView.setOnClickListener {
            //Toast.makeText(context, "Update $id", Toast.LENGTH_SHORT).show()
            context.startActivity(Intent(context, update::class.java).putExtra("notes",item.getID()))
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

}