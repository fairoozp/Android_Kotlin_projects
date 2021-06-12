package com.example.dbs

import android.provider.BaseColumns

object DatabaseContainer{
    class DBS : BaseColumns{
        companion object{
            val TABLE_NAME = "DBS"
            val ID = "ID"
            val DATE = "date"
            val TDD = "tdd"
            val PF = "pf"
            val FC = "fc"
            val ISF = "isf"
            val ICR = "icr"
            val CD = "cd"
        }
    }
}