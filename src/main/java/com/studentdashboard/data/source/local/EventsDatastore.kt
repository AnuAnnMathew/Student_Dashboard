/**
 * This package provides local data source implementations for the student dashboard application,
 * specifically focusing on event and submission data management.
 */

package com.studentdashboard.data.source.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.studentdashboard.data.models.TimeTableEvent
import com.studentdashboard.data.models.TimeTableSubmission
import kotlinx.coroutines.flow.map

/**
 * A local data store class for managing timetable events and submissions using DataStore.
 *
 * This class encapsulates the functionality to save, retrieve, and clear events and submissions
 * data in a persistent storage mechanism. It utilizes DataStore preferences to store data
 * as JSON strings, which are converted to and from Kotlin objects using Gson.
 *
 * @property context The context in which this data store operates, providing access to application-level resources.
 * @property gson An instance of Gson used for converting Kotlin objects to JSON and vice versa.
 */
private val Context.eventsDatastore by preferencesDataStore(name = "events_data_store")

class EventsDatastore(
    private val context: Context,
    private val gson: Gson
) {

    // Preference keys for storing event and submission data
    private val timeTableEventsKey = stringPreferencesKey(name = "time_table_events_key")
    private val timeTableSubmissionsKey = stringPreferencesKey(name = "time_table_submission_key")

    /**
     * A flow that emits the current list of timetable events stored in DataStore.
     *
     * This flow retrieves the JSON string from preferences, parses it into a list of `TimeTableEvent`
     * objects, and returns an empty list if parsing fails or if the string is empty.
     */
    val currentEvents = context.eventsDatastore.data.map { prefs ->
        val asString = prefs[timeTableEventsKey] ?: ""
        if (asString.isNotEmpty()) {
            try {
                val type = object : TypeToken<List<TimeTableEvent>>() {}
                val list: List<TimeTableEvent> = gson.fromJson(asString, type)
                list
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    /**
     * A flow that emits the current list of timetable submissions stored in DataStore.
     *
     * This flow retrieves the JSON string from preferences, parses it into a list of `TimeTableSubmission`
     * objects, and returns an empty list if parsing fails or if the string is empty.
     */
    val currentSubmissions = context.eventsDatastore.data.map { prefs ->
        val asString = prefs[timeTableSubmissionsKey] ?: ""
        if (asString.isNotEmpty()) {
            try {
                val type = object : TypeToken<List<TimeTableSubmission>>() {}
                val list = gson.fromJson(asString, type)
                list
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    /**
     * Saves the provided list of events to the DataStore.
     *
     * The events are converted to a JSON string and stored under the `time_table_events_key`.
     *
     * @param events The list of `TimeTableEvent` objects to be saved.
     */
    suspend fun setCurrentEvents(events: List<TimeTableEvent>) {
        context.eventsDatastore.edit { prefs ->
            val asString = gson.toJson(events)
            prefs[timeTableEventsKey] = asString
        }
    }

    /**
     * Saves the provided list of submissions to the DataStore.
     *
     * The submissions are converted to a JSON string and stored under the `time_table_submission_key`.
     *
     * @param submissions The list of `TimeTableSubmission` objects to be saved.
     */
    suspend fun setCurrentSubmissions(submissions: List<TimeTableSubmission>) {
        context.eventsDatastore.edit { prefs ->
            val asString = gson.toJson(submissions)
            prefs[timeTableSubmissionsKey] = asString
        }
    }

    /**
     * Clears all data stored in the DataStore.
     *
     * This function removes all entries, including events and submissions.
     */
    suspend fun clear() {
        context.eventsDatastore.edit { it.clear() }
    }
}
