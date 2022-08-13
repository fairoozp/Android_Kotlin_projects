package com.example.hilt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Repository {

    private var _test: MutableLiveData<TestClass> = MutableLiveData()
    var test: LiveData<TestClass> = _test
    private var _shared: MutableLiveData<String> = MutableLiveData()
    var shared: LiveData<String> = _shared

    fun setTestValues(name: String, age: Int) {
        _test.postValue(TestClass(name, age))
    }

    fun setShared(sharedText: String){
        _shared.postValue(sharedText)
    }
}