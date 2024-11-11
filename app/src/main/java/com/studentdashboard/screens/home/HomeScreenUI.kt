package com.studentdashboard.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.studentdashboard.R
import com.studentdashboard.data.models.TimeTableEvent
import com.studentdashboard.data.models.TimeTableSubmission
import com.studentdashboard.screens.home.parkingspots.ParkingSpotItemUI
import com.studentdashboard.screens.home.timetable.EventContainerSurfaceUI
import com.studentdashboard.screens.home.timetable.TimeTableEventUI
import com.studentdashboard.screens.home.timetable.TimeTableSubmissionUI
import org.koin.androidx.compose.koinViewModel

/**
 * A composable function that displays the home screen of the student dashboard, including a list
 * of timetable events and submissions, along with available parking spots.
 */
@Composable
fun HomeScreenUI() {
    val viewModel: HomeScreenViewModel = koinViewModel()
    val timeTableEvent by viewModel.timeTable.collectAsStateWithLifecycle()
    val parkingSpots by viewModel.parkingSpots.collectAsStateWithLifecycle()

    LazyColumn(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        // Display the timetable events and submissions
        items(timeTableEvent) { event ->
            EventContainerSurfaceUI(
                isLastItemInDate = event.isLastItemInDate,
                isFirstItemInDate = event.isFirstItemInDate,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(bottom = if (event.isLastItemInDate) 8.dp else 0.dp)
            ) {
                Column(modifier = Modifier.fillParentMaxWidth()) {
                    when (event.item) {
                        is TimeTableEvent ->
                            TimeTableEventUI(
                                item = event.item,
                                isFirstItemInDate = event.isFirstItemInDate,
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .padding(all = 16.dp)
                            )

                        is TimeTableSubmission ->
                            TimeTableSubmissionUI(
                                item = event.item,
                                isFirstItemInDate = event.isFirstItemInDate,
                                modifier = Modifier
                                    .fillParentMaxWidth()
                                    .padding(all = 16.dp)
                            )
                    }

                    // Display separator if needed
                    if (event.showSeparator) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(horizontal = 16.dp),
                            color = Color.Black.copy(alpha = .5f),
                            thickness = Dp.Hairline
                        )
                    }
                }
            }
        }

        // Display the parking spot section title
        item {
            Column(
                modifier = Modifier.fillParentMaxWidth()
            ) {
                HorizontalDivider(thickness = 24.dp, color = Color.Transparent)

                Text(
                    text = stringResource(id = R.string.parking_spot_section_title),
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 16.dp)
                )
            }
        }

        // Display the list of parking spots
        itemsIndexed(parkingSpots) { index, item ->
            ParkingSpotItemUI(
                item = item,
                modifier = Modifier.fillParentMaxWidth(),
                itemIndex = index,
                itemsSize = parkingSpots.size
            )
        }
    }
}
