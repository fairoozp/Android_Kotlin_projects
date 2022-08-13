package com.example.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.data.User
import com.example.roomdatabase.data.UserDatabase
import com.example.roomdatabase.databinding.ActivityAddNewBinding
import com.example.roomdatabase.repository.UserRepository
import com.example.roomdatabase.viewmodel.UserViewModel
import com.example.roomdatabase.viewmodel.UserViewModelFactory

class AddNew : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewBinding
    private lateinit var viewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userRepository = UserRepository(UserDatabase.getDatabase(this))
        val factory = UserViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]

        val firstName = binding.firstNameAdd.text
        val lastName = binding.lastNameAdd.text
        val age = binding.ageAdd.text
        binding.saveButton.setOnClickListener {
            if (firstName.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()){
                viewModel.insert(User(0,firstName.toString().uppercase(),lastName.toString().uppercase(),age.toString()))
                startActivity(Intent(this,MainActivity::class.java))
            }
            else{
                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }

    }
}