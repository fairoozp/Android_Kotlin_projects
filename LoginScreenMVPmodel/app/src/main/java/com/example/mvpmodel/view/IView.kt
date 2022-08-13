package com.example.mvpmodel.view

interface IView {
    fun onLoginSuccess(message: String?)
    fun onLoginError(message: String?)
}