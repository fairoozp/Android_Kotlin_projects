package com.example.unittesting

import android.content.Context

class ResourceCompare {

    /**
     * check give string same as resId
     * */
    fun isEqual(context: Context, resId: Int, string: String): Boolean {
        return context.getString(resId) == string
    }
}