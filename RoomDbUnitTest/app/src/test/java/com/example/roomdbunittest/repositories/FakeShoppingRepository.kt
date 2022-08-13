package com.example.roomdbunittest.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomdbunittest.model.ShoppingItems
import com.example.roomdbunittest.other.Resource
import com.example.roomdbunittest.remote.responses.ImageResponse

class FakeShoppingRepository : ShoppingRepository {

    private val shoppingItem = mutableListOf<ShoppingItems>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItems>>(shoppingItem)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun shouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableShoppingItems.postValue(shoppingItem)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        //return shoppingItem.sumByDouble { it.price.toDouble() }.toFloat()
        return shoppingItem.sumOf { it.price.toDouble() }.toFloat()
    }

    override suspend fun insertShoppingItems(shoppingItems: ShoppingItems) {
        shoppingItem.add(shoppingItems)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItems(shoppingItems: ShoppingItems) {
        shoppingItem.remove(shoppingItems)
        refreshLiveData()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItems>> {
        return observableShoppingItems
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if(shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }
}