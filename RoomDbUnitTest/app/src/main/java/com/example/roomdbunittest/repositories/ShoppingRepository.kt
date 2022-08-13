package com.example.roomdbunittest.repositories

import androidx.lifecycle.LiveData
import com.example.roomdbunittest.model.ShoppingItems
import com.example.roomdbunittest.other.Resource
import com.example.roomdbunittest.remote.responses.ImageResponse

interface ShoppingRepository {

    suspend fun insertShoppingItems(shoppingItems: ShoppingItems)

    suspend fun deleteShoppingItems(shoppingItems: ShoppingItems)

    fun observeAllShoppingItems(): LiveData<List<ShoppingItems>>

    fun observeTotalPrice(): LiveData<Float>

    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}