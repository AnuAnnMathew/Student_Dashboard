package com.studentdashboard.screens.home.timetable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.studentdashboard.extensions.formatLocalDateTimeForList

/**
 * A composable function that displays the formatted date for a timetable entry.
 *
 * @param modifier Modifier to be applied to the date text.
 * @param timestamp The timestamp in milliseconds to be formatted and displayed.
 */
@Composable
fun TimeTableDate(modifier: Modifier = Modifier, timestamp: Long) {
    Row(
        modifier = modifier
            .then(Modifier.fillMaxWidth()
                .padding(bottom = 24.dp, top = 8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = timestamp.formatLocalDateTimeForList(LocalContext.current),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
