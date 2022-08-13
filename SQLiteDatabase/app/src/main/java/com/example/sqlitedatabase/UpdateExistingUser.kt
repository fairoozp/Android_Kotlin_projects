package com.example.sqlitedatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqlitedatabase.databinding.ActivityUpdateExistingUserBinding
import com.example.sqlitedatabase.db.Database

class UpdateExistingUser : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateExistingUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateExistingUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val databaseHelper = Database(this)

        val id = intent.getStringExtra("id").toString()
        val name1 = intent.getStringExtra("name").toString()
        val phone1 = intent.getStringExtra("phone").toString()
        binding.nameUpdate.setText(name1)
        binding.phoneUpdate.setText(phone1)
        binding.updateButton.setOnClickListener {
            val name = binding.nameUpdate.text.toString().uppercase()
            val phone = binding.phoneUpdate.text.toString()
            if (name.isNotEmpty() && phone.isNotEmpty()){
                val result : Boolean = databaseHelper.update(id, name, phone)
                when{
                    result -> Toast.makeText(applicationContext, "Data Updated", Toast.LENGTH_SHORT).show()
                    else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
                }
                startActivity(Intent(this, MainActivity::class.java))
            }
            else{
                Toast.makeText(this, "Fill all the field", Toast.LENGTH_SHORT).show()
            }
        }
        binding.deleteButton.setOnClickListener {
            val result : Boolean = databaseHelper.delete(id)
            when{
                result -> Toast.makeText(applicationContext, "Data Deleted", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}