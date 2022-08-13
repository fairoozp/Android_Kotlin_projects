package com.example.retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.model.MyData
import com.example.retrofit.model.MyDataItem
import com.example.retrofit.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {

    private val repository: MyRepository = MyRepository()
    var temp = MyData()

    var success = false
    private val _data: MutableLiveData<MyData> by lazy {
        MutableLiveData<MyData>()
    }
    lateinit var data: LiveData<MyData>

    init {
        viewModelScope.launch {
            getData()
        }
        loadData()
    }

    suspend fun getData() = repository.getData()

            //_data.value = repository.data

            /*viewModelScope.async {
                *//*val response = repository.getData()
            //response.await()

            response.enqueue(object : Callback<MyData?> {
                override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                    val responseBody = response.body()!!
                    for (myData in responseBody) {
                        temp.add(MyDataItem(myData.body, myData.id, myData.title, myData.userId))
                    }

                    success = true
                }

                override fun onFailure(call: Call<MyData?>, t: Throwable) {
                    Log.e("MyActivity", "onFailure: " + t.message)
                    success = false
                }
            })*//*

        }*/

    fun loadData(){
        if (repository.success) {
            _data.value = repository.data
            data = _data
            success = true
        }
        else {
            success = false
        }
    }
}