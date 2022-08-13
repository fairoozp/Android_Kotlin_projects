package com.example.mvpmodel.model

interface IUser {
    fun getEmail() : String?
    fun getPassword() : String?
    fun checkValid() : Int
}