/**
 * This package contains data models related to items in the timetable list for the student dashboard application.
 *
 * The `TimeTableListItem` data class serves as a representation of an item in the timetable,
 * providing contextual information about its position within the list.
 */

package com.studentdashboard.data.models

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

/**
 * Data class representing an item in the timetable list.
 *
 * This class includes flags to indicate the item's position within the date
 * and whether a separator should be displayed. It also encapsulates the
 * item itself, which can be a submission or an event.
 *
 * @property isFirstItemInDate Indicates whether this item is the first entry for the date in the list.
 * @property isLastItemInDate Indicates whether this item is the last entry for the date in the list.
 * @property showSeparator Indicates if a visual separator should be displayed before this item in the list.
 * @property item The actual timetable item, which can be of type `TimeTableItemType`, representing either a submission or an event.
 */
@Immutable
@Stable
data class TimeTableListItem(
    val isFirstItemInDate: Boolean, // Indicates if this is the first item for the date.
    val isLastItemInDate: Boolean,  // Indicates if this is the last item for the date.
    val showSeparator: Boolean,      // Indicates if a separator should be shown before this item.
    val item: TimeTableItemType      // The item representing either a submission or an event.
)
