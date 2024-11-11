package com.studentdashboard.screens.home.timetable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A composable function that displays the title of an event with styled text.
 *
 * @param modifier Modifier to be applied to the title text.
 * @param title The title text to be displayed.
 */
@Composable
fun EventTitleTextUI(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}
