package com.example.retrofit.repository

import android.util.Log
import com.example.retrofit.api.ApiInterface
import com.example.retrofit.model.MyData
import com.example.retrofit.model.MyDataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MyRepository {

    val data = MyData()
    var success: Boolean = true
    //private val repo = ApiInterface.getInstance()

    //suspend fun getData1() = repo.getData()

    suspend fun getData() = coroutineScope {
        data.add(MyDataItem("hello",1,"There",1))
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getData()


        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(
                call: Call<MyData?>,
                response: Response<MyData?>
            ) {
                val responseBody = response.body()!!
                for (myData in responseBody) {
                    data.add(MyDataItem(myData.body, myData.id, myData.title, myData.userId))
                }
                data.add(MyDataItem("hello",1,"test",1))
                success = true
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.e("MyActivity", "onFailure: " + t.message)
                success = false
            }
        })
    }

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }
}