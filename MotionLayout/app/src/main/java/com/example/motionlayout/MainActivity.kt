package com.example.motionlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.motionlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: ArrayList<User> = ArrayList()
        binding.include.buttonSave.setOnClickListener {
            val name = binding.include.userName.text
            val phone = binding.include.userPhone.text
            val email = binding.include.userEmail.text
            if (name!!.isNotEmpty() && phone!!.isNotEmpty() && email!!.isNotEmpty()) {
                data.add(User(name.toString(), phone.toString(), email.toString()))
                binding.recyclerView.adapter = Adapter(data)
                name.clear()
                phone.clear()
                email.clear()
                val motionLayout: MotionLayout = findViewById(R.id.motionLayout)
                motionLayout.transitionToStart()
            }
            else {
                Toast.makeText(this, "Enter All the Fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = Adapter(data)

    }
}