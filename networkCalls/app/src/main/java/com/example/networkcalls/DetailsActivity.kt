package com.example.networkcalls

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.networkcalls.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val results = intent.getStringExtra("json_results")

        binding.jsonResultsTextview.text = results
    }
}