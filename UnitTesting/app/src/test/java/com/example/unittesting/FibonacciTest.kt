package com.example.unittesting

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FibonacciTest {

    private lateinit var fibonacciClass: Fibonacci

    @Before
    fun `get factorial`() {
        fibonacciClass = Fibonacci()
    }

    @Test
    fun `is fibonacci is true`() {
        val number = 10
        val fibonacci = 55
        val result = fibonacciClass.findFibonacci(number, fibonacci)
        assertThat(result).isTrue()
    }

    @Test
    fun `is fibonacci is false`() {
        val number = 10
        val fibonacci = 100
        val result = fibonacciClass.findFibonacci(number, fibonacci)
        assertThat(result).isFalse()
    }
}