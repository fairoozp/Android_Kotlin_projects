package com.example.dbs

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dbs.DatabaseContainer.DBS.Companion.CD
import com.example.dbs.DatabaseContainer.DBS.Companion.FC
import com.example.dbs.DatabaseContainer.DBS.Companion.ICR
import com.example.dbs.DatabaseContainer.DBS.Companion.DATE
import com.example.dbs.DatabaseContainer.DBS.Companion.ID
import com.example.dbs.DatabaseContainer.DBS.Companion.ISF
import com.example.dbs.DatabaseContainer.DBS.Companion.PF
import com.example.dbs.DatabaseContainer.DBS.Companion.TABLE_NAME
import com.example.dbs.DatabaseContainer.DBS.Companion.TDD
import com.example.dbs.DatabaseContainer.DBS.Companion.TYPE

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "DBS.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val dbs = "CREATE TABLE " + TABLE_NAME + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TYPE + " TEXT, " +
                DATE + " TEXT, " +
                TDD + " TEXT, " +
                PF + " TEXT, " +
                FC + " TEXT, " +
                ISF + " TEXT, " +
                ICR + " TEXT, " +
                CD + " TEXT " + " ) "
        db!!.execSQL(dbs)
    }

    //Insert
    fun insertData(type : String, date : String, tdd1 : String, postFood : String, foodCarb : String, isf1 : String, icr1 : String, cd1 : String) : Boolean {
        val  db : SQLiteDatabase? = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(TYPE,type)
        contentValues.put(DATE,date)
        contentValues.put(TDD,tdd1)
        contentValues.put(PF,postFood)
        contentValues.put(FC,foodCarb)
        contentValues.put(ISF,isf1)
        contentValues.put(ICR,icr1)
        contentValues.put(CD,cd1)

        val insertData = db!!.insert(TABLE_NAME,null,contentValues)
        db.close()

        return !insertData.equals(-1)

    }

    //Raed
    fun readData(): Cursor {
        val db: SQLiteDatabase? = this.writableDatabase
        return db!!.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    //Delete
    fun deleteData(id : String) : Boolean {
        val db : SQLiteDatabase? = this.writableDatabase
        val deleteData = db!!.delete(TABLE_NAME, "$DATE=?", arrayOf(id))
        return deleteData != -1
    }

    //Update
    fun updateData(id : String, date : String, tdd1 : String, postFood : String, foodCarb : String, isf1 : String, icr1 : String, cd1 : String) : Boolean {
        val  db : SQLiteDatabase? = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(DATE,date)
        contentValues.put(TDD,tdd1)
        contentValues.put(PF,postFood)
        contentValues.put(FC,foodCarb)
        contentValues.put(ISF,isf1)
        contentValues.put(ICR,icr1)
        contentValues.put(CD,cd1)
        val updateData = db!!.update(TABLE_NAME, contentValues , "$DATE=?",arrayOf(id))
        db.close()
        return updateData != -1
    }

    //DeleteAll
    fun deleteAll(){
        val  db : SQLiteDatabase? = this.writableDatabase
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }
}