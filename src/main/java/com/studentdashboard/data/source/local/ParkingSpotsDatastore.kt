/**
 * This package provides local data source implementations for the student dashboard application,
 * specifically focusing on parking spot data management.
 */

package com.studentdashboard.data.source.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.studentdashboard.data.models.ParkingSpot
import kotlinx.coroutines.flow.map

/**
 * A local data store class for managing parking spot information using DataStore.
 *
 * This class encapsulates the functionality to save, retrieve, and clear parking spot
 * data in a persistent storage mechanism. It utilizes DataStore preferences to store data
 * as JSON strings, which are converted to and from Kotlin objects using Gson.
 *
 * @property context The context in which this data store operates, providing access to application-level resources.
 * @property gson An instance of Gson used for converting Kotlin objects to JSON and vice versa.
 */
private val Context.parkingSpotsDatastore by preferencesDataStore("parking_spots_datastore")

class ParkingSpotsDatastore(
    private val context: Context,
    private val gson: Gson
) {

    // Preference key for storing parking spot data
    private val parkingSpotsListKey = stringPreferencesKey(name = "parking_spot_list_key")

    /**
     * Saves the provided list of parking spots to the DataStore.
     *
     * The parking spots are converted to a JSON string and stored under the `parking_spot_list_key`.
     *
     * @param data The list of `ParkingSpot` objects to be saved.
     */
    suspend fun setParkingSpots(data: List<ParkingSpot>) {
        context.parkingSpotsDatastore.edit { prefs ->
            val asString = gson.toJson(data)
            prefs[parkingSpotsListKey] = asString
        }
    }

    /**
     * Clears all parking spot data stored in the DataStore.
     *
     * This function removes all entries related to parking spots.
     */
    suspend fun clear() {
        context.parkingSpotsDatastore.edit { it.clear() }
    }

    /**
     * A flow that emits the current list of parking spots stored in DataStore.
     *
     * This flow retrieves the JSON string from preferences, parses it into a list of `ParkingSpot`
     * objects, and returns an empty list if parsing fails or if the string is empty.
     */
    val currentParkingSpots = context.parkingSpotsDatastore.data.map { prefs ->
        val asString = prefs[parkingSpotsListKey] ?: ""

        if (asString.isNotEmpty()) {
            try {
                val type = object : TypeToken<List<ParkingSpot>>() {}
                val list: List<ParkingSpot> = gson.fromJson(asString, type)
                list
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }
}
