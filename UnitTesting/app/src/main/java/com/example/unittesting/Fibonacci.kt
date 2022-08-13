package com.example.unittesting

class Fibonacci {
    fun findFibonacci(
        number: Int,
        fibonacciValue: Int
    ): Boolean {

        fun fibonacci(n: Int): Int {
            var t1 = 0
            var t2 = 1
            for (i in 1..n) {
                val sum = t1 + t2
                t1 = t2
                t2 = sum
            }
            return t1
        }

        if (fibonacciValue != fibonacci(number)) {
            return false
        }
        return true
    }
}