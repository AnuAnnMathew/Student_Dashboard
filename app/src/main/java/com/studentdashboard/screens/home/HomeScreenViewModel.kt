package com.studentdashboard.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.studentdashboard.data.models.ParkingSpot
import com.studentdashboard.data.source.local.EventsDatastore
import com.studentdashboard.data.source.local.UserDataStore
import com.studentdashboard.data.models.TimeTableEvent
import com.studentdashboard.data.models.TimeTableListItem
import com.studentdashboard.data.models.TimeTableSubmission
import com.studentdashboard.data.source.local.ParkingSpotsDatastore
import com.studentdashboard.extensions.normalDateByLocalDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the HomeScreen, managing timetable events and parking spots.
 *
 * This ViewModel interacts with data sources to provide up-to-date information regarding
 * events and submissions related to a student's timetable. It maintains two state flows:
 * - [timeTable]: A list of timetable items that includes both events and submissions,
 *   sorted and grouped by date.
 * - [parkingSpots]: A list of available parking spots.
 *
 * The ViewModel listens for changes in the underlying data sources using Kotlin Coroutines
 * and updates the UI accordingly.
 *
 * Dependencies:
 * - EventsDatastore: Handles the storage and retrieval of event data.
 * - UserDataStore: Manages user-related data (currently not in use in this ViewModel).
 * - ParkingSpotsDatastore: Retrieves parking spot information.
 */
class HomeScreenViewModel(
    private val userDataStore: UserDataStore,
    private val eventsDatastore: EventsDatastore,
    private val parkingSpotsDatastore: ParkingSpotsDatastore
) : ViewModel() {

    // State flows for timetable and parking spots
    private val _timeTable = MutableStateFlow(emptyList<TimeTableListItem>())
    val timeTable: StateFlow<List<TimeTableListItem>> = _timeTable.asStateFlow()

    private val _parkingSpots = MutableStateFlow(emptyList<ParkingSpot>())
    val parkingSpots: StateFlow<List<ParkingSpot>> = _parkingSpots.asStateFlow()

    init {
        // Start listening to events and parking spots when the ViewModel is initialized
        listenToTimeEvents()
        listenToParkingSpots()
    }

    /**
     * Collects parking spot data from the datastore and updates the state flow.
     */
    private fun listenToParkingSpots() {
        viewModelScope.launch(Dispatchers.IO) {
            parkingSpotsDatastore.currentParkingSpots.collectLatest { list ->
                _parkingSpots.update { list }
            }
        }
    }

    /**
     * Collects events and submissions, combining and processing them into a structured timetable.
     *
     * This function combines events and submissions, sorts them by date, groups them by
     * day, and transforms the grouped data into a list of TimeTableListItem objects.
     * It updates the [_timeTable] state flow whenever there are changes.
     */
    private fun listenToTimeEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                eventsDatastore.currentEvents,
                eventsDatastore.currentSubmissions
            ) { events: List<TimeTableEvent>, submissions: List<TimeTableSubmission> ->
                val combinedList = events + submissions

                // Sort the combined list ascending by date
                val sorted = combinedList.sortedBy { item ->
                    when (item) {
                        is TimeTableEvent -> item.startTime
                        is TimeTableSubmission -> item.submissionTime
                    }
                }

                // Group items by date
                val grouped = sorted.groupBy { item ->
                    when (item) {
                        is TimeTableEvent -> item.startTime.normalDateByLocalDateTime
                        is TimeTableSubmission -> item.submissionTime.normalDateByLocalDateTime
                    }
                }

                // Create a list of TimeTableListItems from grouped data
                buildList {
                    grouped.forEach { (_, list) ->
                        list.forEachIndexed { index, timeTableItemType ->
                            add(
                                TimeTableListItem(
                                    isFirstItemInDate = index == 0,
                                    item = timeTableItemType,
                                    isLastItemInDate = index == list.lastIndex,
                                    showSeparator = index >= 0 && index != list.lastIndex
                                )
                            )
                        }
                    }
                }
            }.collectLatest { dataList ->
                _timeTable.update { dataList }
            }
        }
    }
}
