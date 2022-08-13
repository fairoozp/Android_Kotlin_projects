package com.example.roomdatabase.data

import android.content.Context
import androidx.room.*

@Database(version = 1, entities = [User::class], exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object{
        @Volatile
        var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase{
            val tempInstances = INSTANCE
            if (tempInstances != null){
                return tempInstances
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java, "user"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}