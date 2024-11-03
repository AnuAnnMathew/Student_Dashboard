package com.studentdashboard.screens.home.timetable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * A composable function that displays a subtitle for an event with styled text.
 *
 * @param modifier Modifier to be applied to the text.
 * @param text The subtitle text to be displayed.
 */
@Composable
fun EventSubtitleUI(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.DarkGray,
        modifier = modifier
    )
}
