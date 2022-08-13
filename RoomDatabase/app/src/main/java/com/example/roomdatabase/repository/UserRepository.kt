package com.example.roomdatabase.repository

import com.example.roomdatabase.data.User
import com.example.roomdatabase.data.UserDatabase

class UserRepository(private val db : UserDatabase) {

    fun insert(user: User) = db.getUserDao().insertData(user)

    fun update(user: User) = db.getUserDao().updateData(user)

    fun delete(user: User) = db.getUserDao().deleteData(user)

    fun read() = db.getUserDao().readData()

}