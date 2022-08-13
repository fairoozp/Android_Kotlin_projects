package com.example.sqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlitedatabase.databinding.ActivityAddNewUserBinding
import com.example.sqlitedatabase.db.Database

class AddNewUser : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val databaseHelper = Database(this)

        binding.saveButton.setOnClickListener {
            val name = binding.nameAdd.text.toString().uppercase()
            val phone = binding.phoneAdd.text.toString()
            if (name.isNotEmpty() && phone.isNotEmpty()){
                val result : Boolean = databaseHelper.insert(name, phone)
                when{
                    result -> Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
                startActivity(Intent(this, MainActivity::class.java))
            }
            else {
                Toast.makeText(this, "Fill all the field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}