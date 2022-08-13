package com.example.roomdatabase.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(user: User)

    // update
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateData(user: User)

    // delete
    @Delete(entity = User::class)
    fun  deleteData(user: User)

    // read
    @Query("SELECT * FROM user")
    fun readData(): LiveData<List<User>>

}