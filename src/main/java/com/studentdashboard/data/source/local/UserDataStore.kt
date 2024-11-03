/**
 * This package provides local data source implementations for the student dashboard application,
 * specifically focusing on user data management.
 */

package com.studentdashboard.data.source.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

/**
 * A local data store class for managing user data using DataStore.
 *
 * This class encapsulates the functionality to save and retrieve user-related information,
 * specifically the user's name, in a persistent storage mechanism. It utilizes DataStore preferences
 * to store data as key-value pairs.
 *
 * @property context The context in which this data store operates, providing access to application-level resources.
 */
private val Context.userDataStore by preferencesDataStore(name = "user_data_store")

class UserDataStore(private val context: Context) {

    // Preference key for storing the user name
    private val userNameKey = stringPreferencesKey("user_name_key")

    /**
     * A flow that emits the current user name stored in DataStore.
     *
     * This flow retrieves the user name from preferences, returning an empty string if not set.
     */
    val userName = context.userDataStore.data.map { it[userNameKey] ?: "" }

    /**
     * Saves the provided user name to the DataStore.
     *
     * The user name is stored under the `user_name_key`.
     *
     * @param userName The user name to be saved.
     */
    suspend fun setUserName(userName: String) = context.userDataStore.edit { it[userNameKey] = userName }
}
