package com.example.services

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor() : ViewModel() {

    var statusForegroundServices = "Service Status"
    var statusBackgroundServices = "Service Status"

    val statusForegroundServicesLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val statusBackgroundServicesLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        statusBackgroundServicesLiveData.value = statusBackgroundServices
        statusForegroundServicesLiveData.value = statusForegroundServices
    }

}