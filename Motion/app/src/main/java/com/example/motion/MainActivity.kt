package com.example.motion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.motion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.simple.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        binding.car.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }
    }
}