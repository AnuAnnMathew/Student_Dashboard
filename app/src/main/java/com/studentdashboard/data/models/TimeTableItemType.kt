/**
 * This package contains data models related to timetable items within the student dashboard application.
 *
 * The `TimeTableItemType` sealed interface serves as a base for different types of timetable entries,
 * allowing for type-safe representation and pattern matching.
 *
 * The `TimeTableSubmission` data class represents a submission entry in the timetable,
 * encapsulating details about the submission status, title, and the associated submission time.
 *
 * The `TimeTableEvent` data class models an event in the timetable, detailing its title, location,
 * and the start and end times of the event.
 */

package com.studentdashboard.data.models

/**
 * Sealed interface representing a type of item in the timetable.
 * This interface allows for type-safe handling of different kinds of timetable items,
 * such as submissions and events.
 */
sealed interface TimeTableItemType

/**
 * Data class representing a submission entry in the timetable.
 *
 * @property title The title of the submission.
 * @property isSubmitted Indicates whether the submission has been made.
 * @property isClosed Indicates whether the submission period is closed.
 * @property submissionTime The timestamp of when the submission was made, represented in milliseconds since epoch.
 */
data class TimeTableSubmission(
    val title: String,      // The title of the submission.
    val isSubmitted: Boolean, // Indicates if the submission has been completed.
    val isClosed: Boolean,   // Indicates if the submission period has closed.
    val submissionTime: Long // The time when the submission was made, in milliseconds since epoch.
) : TimeTableItemType

/**
 * Data class representing an event entry in the timetable.
 *
 * @property title The title of the event.
 * @property locationName The name of the location where the event takes place.
 * @property startTime The start time of the event, represented in milliseconds since epoch.
 * @property endTime The end time of the event, represented in milliseconds since epoch.
 */
data class TimeTableEvent(
    val title: String,        // The title of the event.
    val locationName: String, // The name of the location where the event is held.
    val startTime: Long,      // The start time of the event, in milliseconds since epoch.
    val endTime: Long         // The end time of the event, in milliseconds since epoch.
) : TimeTableItemType
