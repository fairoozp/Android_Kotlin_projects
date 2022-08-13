package com.example.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.data.User
import com.example.roomdatabase.data.UserDatabase
import com.example.roomdatabase.databinding.ActivityUpdateBinding
import com.example.roomdatabase.repository.UserRepository
import com.example.roomdatabase.viewmodel.UserViewModel
import com.example.roomdatabase.viewmodel.UserViewModelFactory

class Update : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var viewModel : UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id",0)
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val age = intent.getStringExtra("age")

        binding.firstNameUpdate.setText(firstName)
        binding.lastnameUpdate.setText(lastName)
        binding.AgeUpdate.setText(age)

        val userRepository = UserRepository(UserDatabase.getDatabase(this))
        val factory = UserViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]

        binding.deleteButton.setOnClickListener {
            viewModel.delete(User(id,firstName.toString(),lastName.toString(),age.toString()))
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.updateButton.setOnClickListener {
            val firstNameUpdate = binding.firstNameUpdate.text
            val lastNameUpdate = binding.lastnameUpdate.text
            val ageUpdate = binding.AgeUpdate.text
            if (firstNameUpdate.isNotEmpty() && lastNameUpdate.isNotEmpty() && ageUpdate.isNotEmpty()){
                viewModel.update(User(id,firstNameUpdate.toString().uppercase(),lastNameUpdate.toString().uppercase(),ageUpdate.toString()))
                startActivity(Intent(this,MainActivity::class.java))
            }
            else{
                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show()
            }

        }


    }
}