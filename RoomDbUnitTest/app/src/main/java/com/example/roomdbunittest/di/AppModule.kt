package com.example.roomdbunittest.di

import android.content.Context
import androidx.room.Room
import com.example.roomdbunittest.model.ShoppingItemsDao
import com.example.roomdbunittest.model.ShoppingListDb
import com.example.roomdbunittest.other.Constants.BASE_URL
import com.example.roomdbunittest.other.Constants.DATABASE_NAME
import com.example.roomdbunittest.remote.responses.PixabayAPI
import com.example.roomdbunittest.repositories.DefaultShoppingRepository
import com.example.roomdbunittest.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingListDb::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingItemsDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository

    @Singleton
    @Provides
    fun provideShoppingDao(
        database: ShoppingListDb
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }
}