package com.example.roomdbunittest.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.roomdbunittest.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ShoppingItemsDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: ShoppingListDb
    private lateinit var dao: ShoppingItemsDao

    @Before
    fun setup(){
        hiltRule.inject()
        dao = database.shoppingDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertShoppingItems() = runBlockingTest {
        val shoppingItem = ShoppingItems("Apple", 10, 12.5f, "url", id = 1)
        dao.insertShoppingItems(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItems() = runBlockingTest {
        val shoppingItem = ShoppingItems("Apple", 10, 12.5f, "url", id = 1)
        dao.insertShoppingItems(shoppingItem)
        dao.deleteShoppingItems(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun findTotalPrice() = runBlockingTest {
        val shoppingItem1 = ShoppingItems("Apple", 10, 12.5f, "url", id = 1)
        val shoppingItem2 = ShoppingItems("Orange", 8, 8.3f, "url", id = 2)
        val shoppingItem3 = ShoppingItems("Mango", 0, 125f, "url", id = 3)
        dao.insertShoppingItems(shoppingItem1)
        dao.insertShoppingItems(shoppingItem2)
        dao.insertShoppingItems(shoppingItem3)

        val totalPrice = dao.observeTotalPrice().getOrAwaitValue()

        assertThat(totalPrice).isEqualTo(10 * 12.5f + 8 * 8.3f + 0 * 125f)
    }
}

/*

without hilt

package com.example.roomdbunittest.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.roomdbunittest.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingItemsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: ShoppingListDb
    private lateinit var dao: ShoppingItemsDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingListDb::class.java
        ).allowMainThreadQueries().build()
        dao = database.shoppingDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertShoppingItems() = runBlockingTest {
        val shoppingItem = ShoppingItems("Apple", 10, 12.5f, "url", id = 1)
        dao.insertShoppingItems(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItems() = runBlockingTest {
        val shoppingItem = ShoppingItems("Apple", 10, 12.5f, "url", id = 1)
        dao.insertShoppingItems(shoppingItem)
        dao.deleteShoppingItems(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun findTotalPrice() = runBlockingTest {
        val shoppingItem1 = ShoppingItems("Apple", 10, 12.5f, "url", id = 1)
        val shoppingItem2 = ShoppingItems("Orange", 8, 8.3f, "url", id = 2)
        val shoppingItem3 = ShoppingItems("Mango", 0, 125f, "url", id = 3)
        dao.insertShoppingItems(shoppingItem1)
        dao.insertShoppingItems(shoppingItem2)
        dao.insertShoppingItems(shoppingItem3)

        val totalPrice = dao.observeTotalPrice().getOrAwaitValue()

        assertThat(totalPrice).isEqualTo(10 * 12.5f + 8 * 8.3f + 0 * 125f)
    }
}*/
