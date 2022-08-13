package com.example.roomdatabase.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "firstName")
    val firstName : String,
    @ColumnInfo(name = "lastName")
    val lastName : String,
    @ColumnInfo(name = "age")
    val age : String
)
