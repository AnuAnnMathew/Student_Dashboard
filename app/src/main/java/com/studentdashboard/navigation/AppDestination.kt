package com.studentdashboard.navigation

import kotlinx.serialization.Serializable

/**
 * Sealed interface representing the different destinations in the application navigation.
 * This interface is used for type-safe navigation between different screens in the student dashboard application.
 */
@Serializable
sealed interface AppDestination {

    /**
     * Represents the Home destination in the application.
     */
    @Serializable
    data object Home : AppDestination
}
