/**
 * This package contains mock data representations for the student dashboard application,
 * facilitating testing and development with predefined values.
 */

package com.studentdashboard.data

import com.studentdashboard.data.models.ParkingSpot
import com.studentdashboard.data.models.TimeTableEvent
import com.studentdashboard.data.models.TimeTableSubmission

/**
 * An object that holds static mock data used for testing and demonstration purposes within the application.
 *
 * This object includes predefined user display names, events, submissions, and parking spot data that can be utilized
 * throughout the application to simulate real-world scenarios without the need for a backend or database.
 */
object RandomData {

    // A sample display name for user representation
    val userDisplayName = "Kier"

    // A list of mock timetable events with their respective details
    val events = listOf(
        TimeTableEvent(
            title = "Physics Lab",
            locationName = "Science Lab 3",
            startTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000, // Tomorrow
            endTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000 + 3 * 60 * 60 * 1000 // Tomorrow + 3 hours
        ),
        TimeTableEvent(
            title = "Math Seminar",
            locationName = "Math Building, Room 201",
            startTime = System.currentTimeMillis() + 48 * 60 * 60 * 1000, // Two days from now
            endTime = System.currentTimeMillis() + 48 * 60 * 60 * 1000 + 2 * 60 * 60 * 1000 // Two days from now + 2 hours
        ),
        TimeTableEvent(
            title = "Chemistry Lab",
            locationName = "Chemistry Lab 1",
            startTime = System.currentTimeMillis() + 72 * 60 * 60 * 1000, // Three days from now
            endTime = System.currentTimeMillis() + 72 * 60 * 60 * 1000 + 2 * 60 * 60 * 1000 // Three days from now + 2 hours
        ),
        TimeTableEvent(
            title = "English Essay Writing Workshop",
            locationName = "Language Center, Room 101",
            startTime = System.currentTimeMillis() + 48 * 60 * 60 * 1000 + 4 * 60 * 60 * 1000, // Two days from now + 4 hours
            endTime = System.currentTimeMillis() + 48 * 60 * 60 * 1000 + 6 * 60 * 60 * 1000 // Two days from now + 6 hours
        )
    )

    // A list of mock submissions with their details
    val submissions = listOf(
        TimeTableSubmission(
            title = "Pure Math Quiz 3",
            isSubmitted = false,
            isClosed = false,
            submissionTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000 // Tomorrow
        ),
        TimeTableSubmission(
            title = "French Quiz 1",
            isSubmitted = false,
            isClosed = false,
            submissionTime = System.currentTimeMillis() + 48 * 60 * 60 * 1000 // Two days from now
        ),
        TimeTableSubmission(
            title = "Quiz 2",
            isSubmitted = true,
            isClosed = true,
            submissionTime = System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000 // A week from now
        ),
        TimeTableSubmission(
            title = "Biology Report",
            isSubmitted = false,
            isClosed = false,
            submissionTime = System.currentTimeMillis() + 72 * 60 * 60 * 1000 // Three days from now
        )
    )

    // A list of mock parking spots with their reservation status and visitor capacity
    val parkingSpots = listOf(
        ParkingSpot(location = "North (Level 2) - West Wing", reserved = 15, visitor = 20),
        ParkingSpot(location = "South (Ground Floor) - East Wing", reserved = 10, visitor = 12),
        ParkingSpot(location = "Central Courtyard", reserved = 5, visitor = 8)
    )
}
