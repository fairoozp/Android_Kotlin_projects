package com.example.database

import android.provider.BaseColumns

object DatabaseContainer{
    class  PersonTable : BaseColumns{
        companion object{
            val TABLE_NAME = "PersonTable"
            val BaseColumns_ID = "ID"
            val NAME_COLUMN = "NAME"
            val PHONE_COLUMN = "PHONE"
            val EMAIL_COLUMN = "EMAIL"
            val ADDRESS_COLUMN = "ADDRESS"
        }
    }
}