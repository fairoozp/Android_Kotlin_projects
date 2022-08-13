package com.example.whatsappdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Chats : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chats, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = ArrayList<ChatItems>()
        for (i in 1..50){
            data.add(ChatItems("User $i", "Message $i", "10:$i"))
        }

        val recyclerview : RecyclerView = view.findViewById(R.id.chat_list)
        val layoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = layoutManager
        val adapter = context?.let { RecyclerAdapterChat(data , it) }
        recyclerview.adapter = adapter

        val actionButton : FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        actionButton.setOnClickListener {
            Toast.makeText(context, "New chat", Toast.LENGTH_SHORT).show()
            context?.startActivity(Intent(context,NewChat::class.java))
        }

    }
}

data class ChatItems(var username : String , var userMessage : String , var timeMessage :String)