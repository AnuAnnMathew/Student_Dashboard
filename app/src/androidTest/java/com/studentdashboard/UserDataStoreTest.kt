package com.studentdashboard

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.studentdashboard.data.source.local.UserDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UserDataStoreTest is a set of instrumented tests for the UserDataStore class.
 *
 * This class tests the functionality of setting and retrieving the user's name in the UserDataStore,
 * ensuring that the data is stored and retrieved correctly.
 */
@RunWith(AndroidJUnit4::class)
class UserDataStoreTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val userDataStore = UserDataStore(context)

    /**
     * Test to set and retrieve the user's name from the UserDataStore.
     *
     * This method sets a test username ("TestUser") and verifies that the retrieved username
     * matches the expected value using assertions.
     */
    @Test
    fun testUserName() = runBlocking {
        // Set the user's name in the datastore
        userDataStore.setUserName("TestUser")

        // Retrieve and assert that the stored username matches the expected value
        assertEquals("TestUser", userDataStore.userName.first())
    }
}
