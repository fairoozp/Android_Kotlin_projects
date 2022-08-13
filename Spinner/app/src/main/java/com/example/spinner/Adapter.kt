package com.example.spinner

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.spinner.databinding.DropdownBinding

class Adapter(private val dataClass: ArrayList<DataClass>, val listener: (Int) -> Unit) : RecyclerView.Adapter<Adapter.ItemViewHolder>() {

    private lateinit var context: Context
    private lateinit var binding: DropdownBinding

    inner class ItemViewHolder(private val binding: DropdownBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataClass: DataClass) {
            binding.textView.text = dataClass.language
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        binding = DropdownBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataClass[position])
        binding.textView.setOnClickListener {
            listener(position)
        }
    }

    override fun getItemCount(): Int {
        return dataClass.size
    }
}