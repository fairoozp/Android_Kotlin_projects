package com.example.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.database.DatabaseContainer.PersonTable.Companion.ADDRESS_COLUMN
import com.example.database.DatabaseContainer.PersonTable.Companion.BaseColumns_ID
import com.example.database.DatabaseContainer.PersonTable.Companion.EMAIL_COLUMN
import com.example.database.DatabaseContainer.PersonTable.Companion.NAME_COLUMN
import com.example.database.DatabaseContainer.PersonTable.Companion.PHONE_COLUMN
import com.example.database.DatabaseContainer.PersonTable.Companion.TABLE_NAME

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val PersonTable = "CREATE TABLE" + TABLE_NAME + "(" + BaseColumns_ID + "INTEGER PRIMARY KEY AUTOINCREMENT" + NAME_COLUMN + "TEXT" + PHONE_COLUMN + "INTEGER" + EMAIL_COLUMN + "TEXT" + ADDRESS_COLUMN + "TEXT" + ")"

        db!!.execSQL(PersonTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    //Insert
    fun insertData(name : String, phone : String, email : String, address : String) : Boolean {
        val  db : SQLiteDatabase? = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME_COLUMN,name)
        contentValues.put(PHONE_COLUMN,phone)
        contentValues.put(EMAIL_COLUMN,email)
        contentValues.put(ADDRESS_COLUMN,address)

        val inser_data = db!!.insert(TABLE_NAME,null,contentValues)
        db.close()

        return !inser_data.equals(-1)

    }

    //Raed

    companion object{
        private const val DATABASE_NAME = "Person.db"
        private const val DATABASE_VERSION = 1
    }
}
