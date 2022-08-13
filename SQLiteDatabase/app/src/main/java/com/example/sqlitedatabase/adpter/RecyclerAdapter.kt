package com.example.sqlitedatabase.adpter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitedatabase.R
import com.example.sqlitedatabase.UpdateExistingUser
import com.example.sqlitedatabase.model.Person

class RecyclerAdapter(private val context: Context, private val data: ArrayList<Person>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view,parent,false))
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name : TextView = view.findViewById(R.id.userName)
        val phone : TextView = view.findViewById(R.id.userPhone)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = data[position]
        (holder as ViewHolder).name.text = model.name
        holder.phone.text = model.phoneNumber

        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, UpdateExistingUser::class.java)
                .putExtra("id", model.id)
                .putExtra("name", model.name)
                .putExtra("phone", model.phoneNumber)
            ) }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}