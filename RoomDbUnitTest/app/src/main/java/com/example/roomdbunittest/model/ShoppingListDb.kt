package com.example.roomdbunittest.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ShoppingItems::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingListDb : RoomDatabase() {
    abstract fun shoppingDao(): ShoppingItemsDao
}