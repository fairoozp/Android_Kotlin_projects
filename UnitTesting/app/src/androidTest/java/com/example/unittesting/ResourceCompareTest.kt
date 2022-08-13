package com.example.unittesting

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceCompareTest() {

    private lateinit var resourceCompare: ResourceCompare

    /**
     * it will fetch before every test case
     * it will use to open db every time before test case
     * */
    @Before
    fun setup() {
        resourceCompare = ResourceCompare()
    }

    /**
     * it will use for closing db every time after test case
     * */
    @After
    fun tearDown() {

    }

    @Test
    fun isStringResourceSameAsGivenString_ReturnTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "Unit Testing")
        assertThat(result).isTrue()
    }

    @Test
    fun isStringResourceDifferentAsGivenString_ReturnFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "Not Unit Testing")
        assertThat(result).isFalse()
    }
}