package com.example.recyclerviewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recyclerviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var adapter: ItemAdapter? = null
    private val data: ArrayList<Data> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setData()
        adapter = ItemAdapter(data) { position ->
            if (!data[position].selectedIdentifier) {
//                (0 until data.count()).forEach {
//                    data[it].selectedIdentifier = false
//                }
                setData2()
                data[position].selectedIdentifier = true
                adapter?.setItems(data, position)
            }
        }
        binding.rvItems.adapter = adapter
    }

    private fun setData2() {
        data.clear()
        data.apply {
            this.add(Data(false))
            this.add(Data(false))
            this.add(Data(false))
            this.add(Data(false))
            this.add(Data(false))
        }
    }

    private fun setData() {
        data.clear()
        data.apply {
            this.add(Data(true))
            this.add(Data(false))
            this.add(Data(false))
            this.add(Data(false))
            this.add(Data(false))
        }
    }
}