package com.example.unittesting

import java.util.regex.Matcher
import java.util.regex.Pattern

object RegistrationUtil {

    /**
     * input is not valid if
     * ...userName or password or firstName is empty
     * ...password not match confirmedPassword
     * ...password is invalid(6 digit) or not contains letter(minimum 1 cap) & digit & symbol
     */

    private val existingUser = listOf("Fairooz", "Hari")

    fun validateRegistrationInput(
        userName: String,
        password: String,
        confirmedPassword: String,
        firstName: String,
        lastName: String
    ): Boolean {

        val pattern: Pattern
        val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,}\$"
        pattern = Pattern.compile(passwordPattern)
        val passwordMatcher: Matcher = pattern.matcher(password)

        if (userName.trim().isEmpty() || password.trim().isEmpty() || firstName.trim().isEmpty()) {
            return false
        }
        if (userName in existingUser) {
            return false
        }
        if (password != confirmedPassword) {
            return false
        }
        if (!passwordMatcher.matches()) {
            return false
        }
        return true
    }
}