package com.example.stickynotes

import android.provider.BaseColumns

object DatabaseContainer{
    class  StickyNotes : BaseColumns{
        companion object{
            const val TABLE_NAME = "StickyNotes"
            const val ID = "ID"
            const val TITLE = "TITLE"
            const val NOTES = "NOTES"
        }
    }
}