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
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class MainActivity4 : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val f1_next_button: Button = findViewById(R.id.a1_next_button)
        val et_f1_share: EditText = findViewById(R.id.et_a1_share)
        val imageView: ImageView = findViewById(R.id.imageView)
        imageView.setOnClickListener {
            observe()
        }

        f1_next_button.setOnClickListener {
            viewModel.setShared(et_f1_share.text.toString())
            startActivity(Intent(this, MainActivity5::class.java))
        }

    }

    private fun observe() {
        val tv_f1_share: TextView = findViewById(R.id.tv_a1_share)
        viewModel.shared.observeForever {
            tv_f1_share.text = it
        }
    }
}