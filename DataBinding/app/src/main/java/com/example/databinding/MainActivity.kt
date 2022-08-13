package com.example.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var count: Int = 10
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.count = count
        binding.tvCount.setOnClickListener {
            onClick()
        }
        val listAdapter = RecyclerViewAdapter(getDetails())
        binding.rvList.adapter = listAdapter
    }

    private fun onClick() {
        count++
        binding.count = count
    }

    private fun getDetails(): ArrayList<Details> {
        return ArrayList<Details>().apply {
            this.add(Details())
            this.add(Details(name = "Name1", age = "1", email = "name1@gmail.com"))
            this.add(Details())
            this.add(Details(name = "Name2", age = "2", email = "name2@gmail.com"))
            this.add(Details())
            this.add(Details(name = "Name3", age = "3", email = "name3@gmail.com"))
            this.add(Details())
            this.add(Details(name = "Name4", age = "4", email = "name4@gmail.com"))
            this.add(Details())
            this.add(Details(name = "Name5", age = "5", email = "name5@gmail.com"))
            this.add(Details())
            this.add(Details(name = "Name6", age = "6", email = "name6@gmail.com"))
            this.add(Details())
            this.add(Details(name = "Name7", age = "7", email = "name7@gmail.com"))
            this.add(Details())
            this.add(Details(name = "Name8", age = "8", email = "name8@gmail.com"))
            this.add(Details())
            this.add(Details(name = "Name9", age = "9", email = "name9@gmail.com"))
            this.add(Details())
            this.add(Details(name = "Name10", age = "10", email = "name10@gmail.com"))
            this.add(Details())
        }
    }
}