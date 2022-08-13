package com.example.unittesting

class Factorial {
    fun findFactorial(
        number: Int,
        factorialValue: Int
    ): Boolean {

        fun factorial(n: Int): Int {
            return if (n == 1) {
                n.toInt()
            } else {
                n * factorial(n - 1)
            }
        }
        if (factorial(number) != factorialValue) {
            return false
        }
        return true
    }
}