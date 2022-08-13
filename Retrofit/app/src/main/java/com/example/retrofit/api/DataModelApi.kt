package com.example.retrofit.api

import com.example.retrofit.model.MyDataItem
import com.example.retrofit.model.MyDataModel
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body

interface DataModelApi {

    @POST("posts")
    fun createPost(@Body dataModal: MyDataItem): Call<MyDataModel>

    @POST("/api/v1/create")
    suspend fun createEmployee(@Body requestBody: RequestBody): Response<ResponseBody>

}
