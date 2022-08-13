package com.example.roomdatabase.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.Update
import com.example.roomdatabase.data.User

class RecyclerAdapter(private val context: Context, private val data: List<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val firstname : TextView = view.findViewById(R.id.firstName)
        val lastName : TextView = view.findViewById(R.id.lastName)
        val age : TextView = view.findViewById(R.id.age)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = data[position]
        (holder as ViewHolder).firstname.text = model.firstName
        holder.lastName.text = model.lastName
        holder.age.text = model.age

        holder.itemView.setOnClickListener {
            context.startActivity(
                Intent(context, Update::class.java)
                    .putExtra("id", model.id)
                    .putExtra("firstName", model.firstName)
                    .putExtra("lastName", model.lastName)
                    .putExtra("age", model.age)
            ) }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}