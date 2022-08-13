package com.example.mvvmexample.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ViewModel : ViewModel() {

    var email : String = "fairoozp97@gmail.com"
    var password : String = "password"

    val currentEmail : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val currentPassword : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}