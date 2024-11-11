package com.studentdashboard.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.studentdashboard.R

/**
 * A composable function that displays a top app bar UI component.
 *
 * This UI component serves as a header for the application, displaying a personalized
 * greeting message that includes the user's name. The app bar is styled using Material
 * Design principles and adapts to the application's theme.
 *
 * @param modifier A [Modifier] that can be applied to customize the appearance and behavior
 *                 of the TopAppBarUI.
 * @param userName The name of the user to be displayed in the app bar title.
 */
@Composable
fun TopAppBarUI(
    modifier: Modifier = Modifier,
    userName: String,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(6.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.top_bar_title, userName),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
