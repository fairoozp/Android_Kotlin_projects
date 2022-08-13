package com.example.unittesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest {

    @Test
    fun `is valid user`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "CSK",
            "Csk@123456",
            "Csk@123456",
            "Csk",
            ""
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `is empty user`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "Csk@123456",
            "Csk@123456",
            "Csk",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `is empty password`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Csk",
            "",
            "Csk@123456",
            "Csk",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `is mismatch password`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "CSK",
            "Csk@123456",
            "Csk$123456",
            "Csk",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `is invalid password`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "CSK",
            "Csk@123",
            "Csk@123",
            "Csk",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `is empty firstName`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "CSK",
            "Csk@123456",
            "Csk@123456",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `is empty lastName`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "CSK",
            "Csk@123456",
            "Csk@123456",
            "Csk",
            ""
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `is existing User`() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Fairooz",
            "Csk@123456",
            "Csk@123456",
            "Fairooz",
            ""
        )
        assertThat(result).isFalse()
    }
}