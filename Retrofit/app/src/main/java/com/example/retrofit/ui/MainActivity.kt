package com.example.retrofit.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.adapter.MyAdapter
import com.example.retrofit.model.MyData
import com.example.retrofit.databinding.ActivityMainBinding
import com.example.retrofit.model.MyDataItem
import com.example.retrofit.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import okhttp3.internal.wait

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val data = MyData()
    private var success = false

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //data.add(MyDataItem("hello",1,"There",1))
        getMyData()
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
    }

    private fun getMyData() {
        binding.progressBar.visibility = View.VISIBLE

        runBlocking {
            viewModel.getData()
        }

        viewModel.loadData()

        if (viewModel.success){
            viewModel.data.observeForever {
                data.addAll(it)
                applyData()
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }
            success = true
            applyData()
        }
        else {
            success = false
            binding.progressBar.visibility = View.GONE
            binding.failed.text = FAILED
        }
    }

    private fun applyData() {
        binding.progressBar.visibility = View.GONE
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = MyAdapter(data, this)
    }

    companion object {
        const val FAILED = "Failed"
    }
}