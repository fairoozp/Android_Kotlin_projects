package com.example.sqlitedatabase.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sqlitedatabase.db.DatabaseContainer.Person.Companion.ID
import com.example.sqlitedatabase.db.DatabaseContainer.Person.Companion.NAME
import com.example.sqlitedatabase.db.DatabaseContainer.Person.Companion.PHONE_NUMBER
import com.example.sqlitedatabase.db.DatabaseContainer.Person.Companion.TABLE_NAME

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase?) {
        val person = " CREATE TABLE " +
                TABLE_NAME + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                PHONE_NUMBER + " TEXT " + " ) "
        db!!.execSQL(person)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }

    //Insert
    fun insert(name : String, phoneNumber : String) : Boolean{
        val db : SQLiteDatabase? = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME,name)
        contentValues.put(PHONE_NUMBER,phoneNumber)
        val insertData = db!!.insert(TABLE_NAME,null,contentValues)
        db.close()
        @Suppress("EqualsBetweenInconvertibleTypes")
        return !insertData.equals(-1)
    }

    //Raed
    fun read(): Cursor {
        val db: SQLiteDatabase? = this.writableDatabase
        return db!!.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    //Delete
    fun delete(id : String) : Boolean {
        val db : SQLiteDatabase? = this.writableDatabase
        val deleteData = db!!.delete(TABLE_NAME, "$ID=?", arrayOf(id))
        return deleteData != -1
    }

    //Update
    fun update (id : String, name: String, phoneNumber: String) : Boolean {
        val db : SQLiteDatabase? = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME,name)
        contentValues.put(PHONE_NUMBER,phoneNumber)
        val updateData = db!!.update(TABLE_NAME, contentValues , "$ID=?",arrayOf(id))
        db.close()
        return updateData != -1
    }

    //DeleteAll
    fun deleteAll(){
        val  db : SQLiteDatabase? = this.writableDatabase
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object{
        private const val DATABASE_NAME = "Person.db"
        private const val DATABASE_VERSION = 1
    }
}