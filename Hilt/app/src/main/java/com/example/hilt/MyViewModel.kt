package com.example.hilt

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {
    var string: String = "Message from viewModel"

    private val repository = Repository()

    private var _shared: MutableLiveData<String> = MutableLiveData()
    var shared: LiveData<String> = _shared

    fun getText(name: String): String {
        return name
    }

    fun setShared(sharedText: String){
        //_shared.postValue(sharedText)
        repository.setShared(sharedText)
    }

    private var _test: MutableLiveData<TestClass> = MutableLiveData()
    fun observeLiveData(lifecycleOwner: LifecycleOwner) {
        repository.test.observe(lifecycleOwner, {
            _test.postValue(it)
        })
        repository.shared.observe(lifecycleOwner, {
            _shared.postValue(it)
        })
    }
    var test: LiveData<TestClass> = _test

    fun setTestValues(name: String, age: Int) {
        //_test.postValue(TestClass(name, age))
        repository.setTestValues(name, age)
    }

}