package com.example.whatsappdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Status : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = ArrayList<StatusData>()
        for (i in 1..6){
            data.add(StatusData("User $i"))
        }

        val recyclerview : RecyclerView = view.findViewById(R.id.status_view)
        val layoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = layoutManager
        val adapter = context?.let { StatusAdapter(data , it) }
        recyclerview.adapter = adapter


    }
}

data class StatusData (var name : String)