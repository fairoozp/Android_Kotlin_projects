package com.example.dbs

import android.provider.BaseColumns

object DatabaseContainer{
    class DBS : BaseColumns{
        companion object{
            const val TABLE_NAME = "DBS"
            const val ID = "ID"
            const val TYPE = "type"
            const val DATE = "date"
            const val TDD = "tdd"
            const val PF = "pf"
            const val FC = "fc"
            const val ISF = "isf"
            const val ICR = "icr"
            const val CD = "cd"
        }
    }
}