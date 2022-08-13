package com.example.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.databinding.databinding.RecyclerviewBinding

class RecyclerViewAdapter(val details: ArrayList<Details>): RecyclerView.Adapter<DetailsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(RecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(details[position])
    }

    override fun getItemCount(): Int {
        return details.size
    }
}

class DetailsViewHolder(private val binding: RecyclerviewBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(details: Details) {
        binding.details = details
    }
}