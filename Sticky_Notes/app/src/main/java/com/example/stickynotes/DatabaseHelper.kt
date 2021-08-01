package com.example.stickynotes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.stickynotes.DatabaseContainer.StickyNotes.Companion.ID
import com.example.stickynotes.DatabaseContainer.StickyNotes.Companion.NOTES
import com.example.stickynotes.DatabaseContainer.StickyNotes.Companion.TABLE_NAME


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val stickyNotes = " CREATE TABLE " +
                TABLE_NAME + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOTES + " TEXT " + " ) "
        db!!.execSQL(stickyNotes)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }
    //Insert
    @Suppress("EqualsBetweenInconvertibleTypes")
    fun insert(notes : String) : Boolean{
        val db : SQLiteDatabase? = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NOTES,notes)
        val insertData = db!!.insert(TABLE_NAME,null,contentValues)
        db.close()
        return !insertData.equals(-1)
    }

    //Raed
    fun read(): Cursor {
        val db: SQLiteDatabase? = this.writableDatabase
        return db!!.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
    //Delete
    fun delete(notes : String) : Boolean {
        val db : SQLiteDatabase? = this.writableDatabase
        val deleteData = db!!.delete(TABLE_NAME, "$NOTES=?", arrayOf(notes))
        return deleteData != -1
    }
    //Update
    fun update (note : String , notes: String) : Boolean {
        val db : SQLiteDatabase? = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NOTES,notes)
        val updateData = db!!.update(TABLE_NAME, contentValues , "$NOTES=?",arrayOf(note))
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