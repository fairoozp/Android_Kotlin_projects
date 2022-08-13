package com.example.whatsappdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapterMessage (private val context: Context, private var data: ArrayList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    companion object{
        const val Send_Msg = 1
        const val Receive_Msg = 2
        const val Date_Msg = 3
    }

    private inner class SendViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var message : TextView = view.findViewById(R.id.outgoing_msg)
        fun bind(position: Int) {
            val recyclerViewModel = data[position]
            message.text = recyclerViewModel.msg
        }
    }

    private inner class ReceiveViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var message : TextView = view.findViewById(R.id.incoming_msg)
        fun bind(position: Int) {
            val recyclerViewModel = data[position]
            message.text = recyclerViewModel.msg
        }
    }

    private inner class DateViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var message : TextView = view.findViewById(R.id.msg_date)
        fun bind(position: Int) {
            val recyclerViewModel = data[position]
            message.text = recyclerViewModel.msg
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup , msg_type: Int) : RecyclerView.ViewHolder {
        return when (msg_type) {
            Send_Msg -> {
                SendViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_item_right,parent,false))
            }
            Receive_Msg -> {
                ReceiveViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_item_left,parent,false))
            }
            else -> {
                DateViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_date,parent,false))
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (data[position].msg_type) {
            Send_Msg -> {
                (holder as SendViewHolder).bind(position)
            }
            Receive_Msg -> {
                (holder as ReceiveViewHolder).bind(position)
            }
            else -> {
                (holder as DateViewHolder).bind(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].msg_type
    }
}