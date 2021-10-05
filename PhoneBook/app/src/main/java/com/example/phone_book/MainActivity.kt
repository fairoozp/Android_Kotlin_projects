package com.example.phone_book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val floatingActionButton : FloatingActionButton = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, Add::class.java))
        }

    }
}
