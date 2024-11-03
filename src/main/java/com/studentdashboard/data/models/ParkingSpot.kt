/**
 * This package contains data models related to parking spots within the student dashboard application.
 *
 * The `ParkingSpot` data class represents an individual parking spot with its attributes,
 * while the `ParkingSpotType` enum class defines the different types of parking spots available in the application.
 *
 * The `ParkingSpot` class is marked as `@Stable` and `@Immutable` to indicate that instances
 * are meant to be used in a Compose context, providing stability and immutability guarantees
 * which are essential for performance optimizations in Jetpack Compose.
 *
 * The `ParkingSpotType` enum class associates each type of parking spot with a unique identifying letter
 * and a corresponding background color for UI representation.
 *
 * The predefined colors for reserved and visitor parking spots are imported from the UI theme package.
 */

package com.studentdashboard.data.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.studentdashboard.ui.theme.parkingReservedColor
import com.studentdashboard.ui.theme.parkingVisitorColor

@Stable
@Immutable
data class ParkingSpot(
    val location: String, // The location identifier for the parking spot.
    val reserved: Int,    // The number of reserved instances for this parking spot.
    val visitor: Int      // The number of visitor instances for this parking spot.
)

/**
 * Enum class representing the different types of parking spots.
 *
 * Each parking spot type has an identifying letter and a background color for its representation
 * in the user interface.
 */
enum class ParkingSpotType(
    val identifyingLetter: String, // The letter that identifies the parking spot type.
    val backgroundColor: Color      // The color representing the parking spot type in the UI.
) {
    Reserved("R", parkingReservedColor), // Represents a reserved parking spot.
    Visitor("B", parkingVisitorColor)    // Represents a visitor parking spot.
}
