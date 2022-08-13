package com.example.roomdbunittest.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingItemsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingItems(shoppingItems: ShoppingItems)

    @Delete
    suspend fun deleteShoppingItems(shoppingItems: ShoppingItems)

    @Query("SELECT * FROM shopping_items")
    fun observeAllShoppingItems(): LiveData<List<ShoppingItems>>

    @Query("SELECT SUM(price * amount) FROM shopping_items")
    fun observeTotalPrice(): LiveData<Float>
}