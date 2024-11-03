/**
 * Dependency Injection Module for the student dashboard application.
 *
 * This module is responsible for providing singleton instances of the data store classes
 * used throughout the application. By utilizing Koin for dependency injection,
 * the application promotes a modular architecture and simplifies testing by allowing
 * easy substitution of dependencies.
 */

package com.studentdashboard.di

import com.studentdashboard.data.source.local.EventsDatastore
import com.studentdashboard.data.source.local.ParkingSpotsDatastore
import com.studentdashboard.data.source.local.UserDataStore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

/**
 * Koin module defining the single instances of data store classes.
 *
 * The `dataStoresModule` provides the following dependencies:
 * - [UserDataStore]: Manages user-specific data such as usernames.
 * - [EventsDatastore]: Handles storage and retrieval of event-related data.
 * - [ParkingSpotsDatastore]: Manages parking spot information.
 */
val dataStoresModule = module {
    singleOf(::UserDataStore)
    singleOf(::EventsDatastore)
    singleOf(::ParkingSpotsDatastore)
}
