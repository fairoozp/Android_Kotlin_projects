package com.example.stickynotes

import android.provider.BaseColumns

object DatabaseContainer{
    class  StickyNotes : BaseColumns{
        companion object{
            const val TABLE_NAME = "Sticky Notes"
            const val ID = "ID"
            const val NOTES = "NOTES"
        }
    }
}