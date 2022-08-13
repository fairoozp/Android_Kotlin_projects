package com.example.animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutTransition: Button = findViewById(R.id.transition)
        layoutTransition.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }

        val motionLayout: Button = findViewById(R.id.motionLayout)
        motionLayout.setOnClickListener {
            startActivity(Intent(this, MainActivity3::class.java))
        }

        val constraintSet: Button = findViewById(R.id.constraintSet)
        constraintSet.setOnClickListener {
            startActivity(Intent(this, MainActivity4::class.java))
        }

    }
}