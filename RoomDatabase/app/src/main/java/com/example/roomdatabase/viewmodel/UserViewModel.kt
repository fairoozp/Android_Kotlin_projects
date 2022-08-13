package com.example.roomdatabase.viewmodel

import androidx.lifecycle.ViewModel
import com.example.roomdatabase.data.User
import com.example.roomdatabase.repository.UserRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun insert(user: User) = GlobalScope.launch {
        repository.insert(user)
    }
    fun update(user: User) = GlobalScope.launch {
        repository.update(user)
    }
    fun delete(user: User) = GlobalScope.launch {
        repository.delete(user)
    }
    fun read() = repository.read()

}