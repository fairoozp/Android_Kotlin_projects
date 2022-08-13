package com.example.mvpmodel.presenter

import com.example.mvpmodel.model.User
import com.example.mvpmodel.view.IView

class Presenter(private val loginView : IView) : IPresenter {
    override fun onLogin(email: String?, password: String?) {
        val user = User(email , password)

        when(user.checkValid()){
            0 -> {
                loginView.onLoginError("Please enter Email address")
            }
            1 -> {
                loginView.onLoginError("Please enter a valid Email address")
            }
            2 -> {
                loginView.onLoginError("Please enter the password")
            }
            3 -> {
                loginView.onLoginError("Please enter a strong password\nHint : Enter more than 6 character")
            }
            4 -> {
                loginView.onLoginError("User does not exist, Please try again")
            }
            5 -> {
                loginView.onLoginError("Wrong password, check password and try again")
            }
            else -> {
                loginView.onLoginSuccess("Login Successful\nWelcome Fairooz")
            }
        }
    }
}