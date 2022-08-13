package com.example.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.utils.databinding.ActivityMainBinding
import com.example.utils.utils.InputValidator
import com.example.utils.utils.MessageUtil
import com.example.utils.utils.NetworkUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val checkInternet : Button = findViewById(R.id.checkInternet)
        //val displayStatus : TextView = findViewById(R.id.displayStatus)

        binding.checkInternet.setOnClickListener {
            val result = NetworkUtil.isInternetAvailable(this)
            if (result) {
                binding.displayStatus.text = SUCCESS
            }
            else {
                binding.displayStatus.text = FAILED
            }
        }

        //val validateInput : EditText = findViewById(R.id.validateInput)
        //val checkInput : Button = findViewById(R.id.checkInput)

        binding.checkInput.setOnClickListener {
            val result = InputValidator.isRequiredFieldNotEmpty(binding.validateInput.text.toString().uppercase())
            if (result) {
                MessageUtil.longToast(this, "$IS_NOT_EMPTY ${binding.validateInput.text.toString().uppercase()}")
            }
            else {
                MessageUtil.shortToast(this, IS_EMPTY)
            }
        }

    }

    companion object {
        const val SUCCESS = "You have internet connection"
        const val FAILED = "You don't have any internet connection,\nCheck your connection and try again"
        const val IS_NOT_EMPTY = "Your name is"
        const val IS_EMPTY = "EditText field is empty"
    }
}