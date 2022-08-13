package com.example.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.adapter.RecyclerAdapter
import com.example.roomdatabase.data.UserDatabase
import com.example.roomdatabase.databinding.ActivityMainBinding
import com.example.roomdatabase.repository.UserRepository
import com.example.roomdatabase.viewmodel.UserViewModel
import com.example.roomdatabase.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepository = UserRepository(UserDatabase.getDatabase(this))
        val factory = UserViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]

        binding.addNew.setOnClickListener {
            startActivity(Intent(this,AddNew::class.java))
        }

        loadData()

    }

    private fun loadData() {

        viewModel.read().observeForever {
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = RecyclerAdapter(this,it)
        }
    }
}