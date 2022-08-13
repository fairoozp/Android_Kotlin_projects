package com.example.hilt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged

class MainActivity5 : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val f1_next_button: Button = findViewById(R.id.a2_next_button)
        val et_f1_share: EditText = findViewById(R.id.et_a2_share)
        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            observe()
        }

        f1_next_button.setOnClickListener {
            viewModel.setShared(et_f1_share.text.toString())
            startActivity(Intent(this, MainActivity4::class.java))
        }
    }

    private fun observe() {
        val tv_f1_share: TextView = findViewById(R.id.tv_a2_share)
        viewModel.shared.observeForever {
            tv_f1_share.text = it
        }
    }
}