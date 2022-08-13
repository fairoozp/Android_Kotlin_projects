package com.example.whatsappdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.up_home_arrow)
        }

        val user = intent.getStringExtra("name")
        val nameText : TextView = findViewById(R.id.nameText)
        nameText.text = user

        val data = ArrayList<Message>()

        data.also {
            it.add(Message(RecyclerAdapterMessage.Date_Msg,"27/10/21"))
            it.add(Message(RecyclerAdapterMessage.Send_Msg,"Hi !"))
            it.add(Message(RecyclerAdapterMessage.Send_Msg,"How are you ?"))
            it.add(Message(RecyclerAdapterMessage.Date_Msg,"28/10/21"))
            it.add(Message(RecyclerAdapterMessage.Receive_Msg,"I am good"))
            it.add(Message(RecyclerAdapterMessage.Receive_Msg,"What about you"))
            it.add(Message(RecyclerAdapterMessage.Send_Msg,"Me too"))
        }

        val recyclerview : RecyclerView = findViewById(R.id.chat_recyclerview)
        val layoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = layoutManager
        val adapter = RecyclerAdapterMessage(this , data)
        recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
        recyclerview.scrollToPosition(data.size -1)


        val input : EditText = findViewById(R.id.input)
        val sendButton : ImageButton = findViewById(R.id.send_button)
        sendButton.setOnClickListener {
            data.add(Message(RecyclerAdapterMessage.Send_Msg,"${input.text}"))
            input.text.clear()
            adapter.notifyDataSetChanged()
            recyclerview.scrollToPosition(data.size -1)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.chat_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.video -> {
                Toast.makeText(this, "click on Video Button", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.audio -> {
                Toast.makeText(this, "Clicked on Audio Button", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

data class Message (var msg_type : Int , var msg : String)