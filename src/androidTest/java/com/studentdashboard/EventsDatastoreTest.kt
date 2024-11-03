package com.studentdashboard

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.studentdashboard.data.models.TimeTableEvent
import com.studentdashboard.data.models.TimeTableSubmission
import com.studentdashboard.data.source.local.EventsDatastore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * EventsDatastoreTest is a set of instrumented tests for the EventsDatastore class.
 *
 * This class tests the functionality of setting and retrieving current events and submissions
 * in the EventsDatastore, ensuring that the data is stored and retrieved correctly.
 *
 * Tests:
 * - testSetAndGetCurrentEvents: Verifies that the current events can be set and retrieved
 *   accurately.
 * - testSetAndGetCurrentSubmissions: Verifies that the current submissions can be set and
 *   retrieved accurately.
 * - testEmptyEventsAndSubmissions: Checks that the datastore returns empty lists when no
 *   events or submissions are set.
 */
@RunWith(AndroidJUnit4::class)
class EventsDatastoreTest {

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val gson = Gson()
    private val eventsDatastore = EventsDatastore(context, gson)

    @Test
    fun testSetAndGetCurrentEvents() = runBlocking {
        // Create a list of test TimeTableEvent objects
        val testEvents = listOf(
            TimeTableEvent("Event1", "Location1", 1631399694000, 1631403294000), // example timestamps
            TimeTableEvent("Event2", "Location2", 1631406894000, 1631410494000)
        )

        // Set the current events
        eventsDatastore.setCurrentEvents(testEvents)

        // Retrieve the current events
        val retrievedEvents = eventsDatastore.currentEvents.first()

        // Assert that the retrieved events match the original
        assertEquals(testEvents.size, retrievedEvents.size)
        assertEquals(testEvents[0].title, retrievedEvents[0].title)
        assertEquals(testEvents[0].locationName, retrievedEvents[0].locationName)
        assertEquals(testEvents[1].title, retrievedEvents[1].title)
        assertEquals(testEvents[1].locationName, retrievedEvents[1].locationName)
    }

    @Test
    fun testSetAndGetCurrentSubmissions() = runBlocking {
        // Create a list of test TimeTableSubmission objects
        val testSubmissions = listOf(
            TimeTableSubmission("Submission1", isSubmitted = true, false, 1631399694000), // example timestamp
            TimeTableSubmission("Submission2", isSubmitted = false, true, 1631403294000)
        )

        // Set the current submissions
        eventsDatastore.setCurrentSubmissions(testSubmissions)

        // Retrieve the current submissions
        val retrievedSubmissions = eventsDatastore.currentSubmissions.first()

        // Assert that the retrieved submissions match the original
        assertEquals(testSubmissions.size, retrievedSubmissions.size)
        assertEquals(testSubmissions[0].title, retrievedSubmissions[0].title)
        assertEquals(testSubmissions[0].isSubmitted, retrievedSubmissions[0].isSubmitted)
        assertEquals(testSubmissions[0].isClosed, retrievedSubmissions[0].isClosed)
        assertEquals(testSubmissions[1].title, retrievedSubmissions[1].title)
        assertEquals(testSubmissions[1].isSubmitted, retrievedSubmissions[1].isSubmitted)
        assertEquals(testSubmissions[1].isClosed, retrievedSubmissions[1].isClosed)
    }

    @Test
    fun testEmptyEventsAndSubmissions() = runBlocking {
        // Retrieve current events and submissions when nothing is set
        eventsDatastore.clear()
        val retrievedEvents = eventsDatastore.currentEvents.first()
        val retrievedSubmissions = eventsDatastore.currentSubmissions.first()

        // Assert that the retrieved lists are empty
        assertEquals(0, retrievedEvents.size)
        assertEquals(0, retrievedSubmissions.size)
    }
}
