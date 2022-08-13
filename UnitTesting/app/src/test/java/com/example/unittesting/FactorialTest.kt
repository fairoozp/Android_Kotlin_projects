package com.example.unittesting

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class FactorialTest {

    private lateinit var factorialClass: Factorial

    @Before
    fun `get factorial`() {
        factorialClass = Factorial()
    }

    @Test
    fun `is factorial is true`() {
        val number = 5
        val factorial = 120
        val result = factorialClass.findFactorial(number, factorial)
        assertThat(result).isTrue()
    }

    @Test
    fun `is factorial is false`() {
        val number = 5
        val factorial = 15
        val result = factorialClass.findFactorial(number, factorial)
        assertThat(result).isFalse()
    }
}