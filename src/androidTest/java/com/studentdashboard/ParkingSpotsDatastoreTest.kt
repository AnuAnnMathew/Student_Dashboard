package com.studentdashboard

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.studentdashboard.data.models.ParkingSpot
import com.studentdashboard.data.source.local.ParkingSpotsDatastore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * ParkingSpotsDatastoreTest is a set of instrumented tests for the ParkingSpotsDatastore class.
 *
 * This class tests the functionality of setting and retrieving parking spots in the ParkingSpotsDatastore,
 * ensuring that the data is stored and retrieved correctly.
 *
 * Tests:
 * - testSetAndGetParkingSpots: Verifies that the parking spots can be set and retrieved accurately.
 * - testEmptyParkingSpots: Checks that the datastore returns an empty list when no parking spots are set.
 */
@RunWith(AndroidJUnit4::class)
class ParkingSpotsDatastoreTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val gson = Gson()
    private val parkingSpotsDatastore = ParkingSpotsDatastore(context, gson)

    @Test
    fun testSetAndGetParkingSpots() = runBlocking {
        // Create a list of test ParkingSpot objects
        val testParkingSpots = listOf(
            ParkingSpot("Spot A", 1, 3),
            ParkingSpot("Spot B", 2, 5)
        )

        // Set the parking spots
        parkingSpotsDatastore.setParkingSpots(testParkingSpots)

        // Retrieve the current parking spots
        val retrievedParkingSpots = parkingSpotsDatastore.currentParkingSpots.first()

        // Assert that the retrieved parking spots match the original
        assertEquals(testParkingSpots.size, retrievedParkingSpots.size)
        assertEquals(testParkingSpots[0].location, retrievedParkingSpots[0].location)
        assertEquals(testParkingSpots[0].reserved, retrievedParkingSpots[0].reserved)
        assertEquals(testParkingSpots[0].visitor, retrievedParkingSpots[0].visitor)
        assertEquals(testParkingSpots[1].location, retrievedParkingSpots[1].location)
        assertEquals(testParkingSpots[1].reserved, retrievedParkingSpots[1].reserved)
        assertEquals(testParkingSpots[1].visitor, retrievedParkingSpots[1].visitor)
    }

    @Test
    fun testEmptyParkingSpots() = runBlocking {
        // Retrieve current parking spots when nothing is set
        parkingSpotsDatastore.clear()
        val retrievedParkingSpots = parkingSpotsDatastore.currentParkingSpots.first()

        // Assert that the retrieved list is empty
        assertEquals(0, retrievedParkingSpots.size)
    }
}
