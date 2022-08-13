package com.example.mvpmodel.model

import android.text.TextUtils
import android.util.Patterns


class User(private val email: String?, private val password: String?) : IUser {

    private val userEmail = "fairoozp97@gmail.com"
    private val userPassword = "password"

    override fun getEmail(): String? {
        return email
    }

    override fun getPassword(): String? {
        return password
    }

    override fun checkValid(): Int {
        return if (TextUtils.isEmpty(getEmail())) 0
        else if(!Patterns.EMAIL_ADDRESS.matcher(getEmail().toString()).matches()) 1
        else if (TextUtils.isEmpty(getPassword())) 2
        else if (getPassword()?.length!! <=6) 3
        else {
            if (getEmail()==userEmail && getPassword() == userPassword) -1
            else if (getEmail() == userEmail && getPassword() != userPassword) 5
            else 4
        }
    }
}
