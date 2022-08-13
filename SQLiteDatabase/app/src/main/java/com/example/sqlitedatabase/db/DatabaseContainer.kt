package com.example.sqlitedatabase.db

import android.provider.BaseColumns

object DatabaseContainer {
    class Person : BaseColumns{
        companion object{
            const val TABLE_NAME = "PersonDetails"
            const val ID = "ID"
            const val NAME = "Name"
            const val PHONE_NUMBER = "PhoneNumber"
        }
    }
}