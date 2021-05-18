package com.example.database

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add : FloatingActionButton = findViewById(R.id.add)
        add.setOnClickListener {
            startActivity( Intent(this, MainActivity2::class.java))
        }

    }

    override fun onBackPressed() {
        finishAffinity()
    }
}