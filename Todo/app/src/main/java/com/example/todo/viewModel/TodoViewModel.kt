package com.example.todo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.model.TodoModel
import com.example.todo.repository.TodoRepository

class TodoViewModel : ViewModel() {

    private val repo = TodoRepository()
    val data = repo.todo


    val currentData : MutableLiveData<ArrayList<TodoModel>> by lazy {
        MutableLiveData<ArrayList<TodoModel>>()
    }
    init {
        getData()
    }

    private fun getData() {
        currentData.value = data
    }

}