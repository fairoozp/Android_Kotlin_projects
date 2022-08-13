package com.example.sharepreference

import android.text.TextUtils

object InputValidator {

    fun isRequiredFieldNotEmpty(inputField: String): Boolean {
        return !TextUtils.isEmpty(inputField)
    }

}