package com.example.retrofit.api

import com.example.retrofit.model.MyData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    //@GET("posts")
    //fun getData(): Call<MyData>

    @GET("posts")
    fun getData() : Call<MyData>

    /*companion object {

        var retrofitService: ApiInterface? = null

        fun getInstance() : ApiInterface {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiInterface::class.java)
            }
            return retrofitService!!
        }
    }*/

}