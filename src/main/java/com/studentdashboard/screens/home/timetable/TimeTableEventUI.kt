package com.studentdashboard.screens.home.timetable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.studentdashboard.data.models.TimeTableEvent
import com.studentdashboard.extensions.timeInsideDayAmPm
import com.studentdashboard.ui.theme.eventTimeBoxColor

/**
 * A composable function that displays an event in the timetable, including its start and end times,
 * title, and location name. The date is displayed only for the first item in the date.
 *
 * @param modifier Modifier to be applied to the overall layout of the event.
 * @param item The TimeTableEvent containing details of the event to be displayed.
 * @param isFirstItemInDate A boolean indicating whether this is the first item of the date,
 *                           affecting whether the date is displayed.
 */
@Composable
fun TimeTableEventUI(
    modifier: Modifier = Modifier,
    item: TimeTableEvent,
    isFirstItemInDate: Boolean
) {
    Column(
        modifier = modifier
    ) {
        // Display the date if this is the first item of the date
        if (isFirstItemInDate) {
            TimeTableDate(
                timestamp = item.startTime
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.weight(.35f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .fillMaxHeight()
                        .background(color = eventTimeBoxColor, shape = RoundedCornerShape(24.dp))
                )

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.startTime.timeInsideDayAmPm,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "—${item.endTime.timeInsideDayAmPm}",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
            Column(
                modifier = Modifier.weight(.65f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                EventTitleTextUI(title = item.title)
                EventSubtitleUI(text = item.locationName)
            }
        }
    }
}
