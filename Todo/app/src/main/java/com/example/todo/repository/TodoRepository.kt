package com.example.todo.repository

import com.example.todo.model.TodoModel

class TodoRepository {
    val todo = ArrayList<TodoModel>()
    init {
        for (i in 1..5){
            todo.add(TodoModel("Item $i"))
        }
    }
}