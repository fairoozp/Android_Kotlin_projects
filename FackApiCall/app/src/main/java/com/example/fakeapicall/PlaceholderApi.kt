package com.example.fakeapicall

import retrofit2.http.GET
import io.reactivex.Single

interface PlaceholderApi {

    @GET("posts")
    fun getPosts(): Single<List<Post>>
}