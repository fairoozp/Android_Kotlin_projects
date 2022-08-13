package com.example.recyclerviewbinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewbinding.databinding.ItemsBinding

class ItemAdapter(private var data: ArrayList<Data>, val listener: (Int) -> Unit) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    lateinit var binding: ItemsBinding
    lateinit var context: Context

    class ViewHolder(binding: ItemsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data, binding: ItemsBinding) {
            if (data.selectedIdentifier) {
                binding.clUnselected.visibility = View.GONE
                binding.clSelected.visibility = View.VISIBLE
            } else {
                binding.clUnselected.visibility = View.VISIBLE
                binding.clSelected.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        binding = ItemsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bind(data[position], binding)
        binding.clUnselected.setOnClickListener {
            listener(position)
            Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
        }
        binding.clSelected.setOnClickListener {
            listener(position)
            Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
    fun setItems(tagSelection: ArrayList<Data>, position: Int) {
        this.data = tagSelection
        for (i in 0 until data.size){
            this.notifyItemChanged(i)
        }
        //this.notifyItemChanged(position)
        //this.notifyItemRangeChanged(0, data.size)
        //this.notifyDataSetChanged()
    }
}