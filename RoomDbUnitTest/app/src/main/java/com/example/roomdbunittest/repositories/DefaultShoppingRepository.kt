package com.example.roomdbunittest.repositories

import androidx.lifecycle.LiveData
import com.example.roomdbunittest.model.ShoppingItems
import com.example.roomdbunittest.model.ShoppingItemsDao
import com.example.roomdbunittest.other.Resource
import com.example.roomdbunittest.remote.responses.ImageResponse
import com.example.roomdbunittest.remote.responses.PixabayAPI
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingItemsDao: ShoppingItemsDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository {
    override suspend fun insertShoppingItems(shoppingItems: ShoppingItems) {
        shoppingItemsDao.insertShoppingItems(shoppingItems)
    }

    override suspend fun deleteShoppingItems(shoppingItems: ShoppingItems) {
        shoppingItemsDao.deleteShoppingItems(shoppingItems)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItems>> {
        return shoppingItemsDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingItemsDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred", null)
            } else {
                Resource.error("An unknown error occurred", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}