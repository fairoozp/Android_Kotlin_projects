package com.example.headerfooter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class   Adapter (private val data : ArrayList<List>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val header : TextView = view.findViewById(R.id.headerTitle)
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val number : TextView = view.findViewById(R.id.numberValue)
    }

    inner class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val  footer : TextView = view.findViewById(R.id.footerTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            1 -> {
                return ItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
                )
            }
            2 -> {
                return HeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.header, parent, false)
                )
            }
            else -> {
                return FooterViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.footer, parent, false)
                )
            }
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(data[position].viewType){
            1 -> {
                val model = data[position]
                (holder as ItemViewHolder).number.text = model.number
            }
            2 -> {
                val model = data[position]
                (holder as HeaderViewHolder).header.text = model.number
            }
            else -> {
                val model = data[position]
                (holder as FooterViewHolder).footer.text = model.number
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType
    }

}