package com.example.motionlayout

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val data: ArrayList<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.userNameAdapter)
        val phone: TextView = view.findViewById(R.id.userPhoneAdapter)
        val email: TextView = view.findViewById(R.id.userEmailAdapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.name.text = data[position].name
        holder.phone.text = data[position].phone
        holder.email.text = data[position].email
    }

    override fun getItemCount(): Int {
        return data.size
    }
}