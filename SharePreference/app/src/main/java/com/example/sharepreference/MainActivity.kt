package com.example.sharepreference

import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sharepreference.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPreference.edit()

        loadPreferences(sharedPreference)

        binding.savePreferences.setOnClickListener {
            val nameField = InputValidator.isRequiredFieldNotEmpty(binding.name.text.toString())
            val ageField = InputValidator.isRequiredFieldNotEmpty(binding.age.text.toString())
            if (nameField && ageField ){
                editor.apply {
                    putString("name", binding.name.text.toString().uppercase())
                    putInt("age", binding.age.text.toString().toInt())
                    apply()
                }
                binding.name.text.clear()
                binding.age.text.clear()
                Toast.makeText(this, SUCCESS, Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, FAILED, Toast.LENGTH_SHORT).show()
            }
        }

        binding.refreshButton.setOnClickListener {
            loadPreferences(sharedPreference)
        }

        binding.clearButton.setOnClickListener {
            editor.clear().apply()
        }

        binding.toggleTheme.setOnClickListener {
            val theme = binding.toggleTheme.isChecked
            if (theme) {
                editor.apply {
                    editor.putBoolean("theme", true)
                    apply()
                }
                setDarkTheme()
            }
            else {
                editor.apply {
                    editor.putBoolean("theme", false)
                    apply()
                }
                setLightTheme()
            }
        }


    }

    private fun loadPreferences(sharedPreferences: SharedPreferences) {
        val name = sharedPreferences.getString("name", "Name : No data")
        val age = sharedPreferences.getInt("age", 0)
        val theme = sharedPreferences.getBoolean("theme", false)
        binding.sharedName.text = name
        binding.sharedAge.text = age.toString()
        binding.toggleTheme.isChecked = theme
        if (theme){
            setDarkTheme()
        }
        else{
            setLightTheme()
        }
    }

    private fun setDarkTheme() {
        binding.layout.setBackgroundColor(Color.BLACK)
        binding.sharedName.setTextColor(Color.WHITE)
        binding.sharedAge.setTextColor(Color.WHITE)
        binding.textView.setTextColor(Color.WHITE)
        binding.name.apply {
            this.setTextColor(Color.WHITE)
            this.setHintTextColor(Color.GRAY)
        }
        binding.age.apply {
            this.setTextColor(Color.WHITE)
            this.setHintTextColor(Color.GRAY)
        }
        /*binding.savePreferences.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.setTextColor(Color.WHITE)
        }
        binding.refreshButton.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.setTextColor(Color.WHITE)
        }
        binding.clearButton.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.setTextColor(Color.WHITE)
        }
        binding.toggleTheme.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.setTextColor(Color.WHITE)
        }*/
    }

    private fun setLightTheme() {
        binding.layout.setBackgroundColor(Color.WHITE)
        binding.sharedName.setTextColor(Color.BLACK)
        binding.sharedAge.setTextColor(Color.BLACK)
        binding.textView.setTextColor(Color.BLACK)
        binding.name.apply {
            this.setTextColor(Color.BLACK)
            this.setHintTextColor(Color.GRAY)
        }
        binding.age.apply {
            this.setTextColor(Color.BLACK)
            this.setHintTextColor(Color.GRAY)
        }
        /*binding.savePreferences.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.setTextColor(Color.BLACK)
        }
        binding.refreshButton.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.setTextColor(Color.BLACK)
        }
        binding.clearButton.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.setTextColor(Color.BLACK)
        }
        binding.toggleTheme.apply {
            this.setBackgroundColor(Color.TRANSPARENT)
            this.setTextColor(Color.BLACK)
        }*/
    }

    companion object {
        const val SUCCESS = "Data saved successfully"
        const val FAILED = "Failed to save data\nFill all the field"
    }
}