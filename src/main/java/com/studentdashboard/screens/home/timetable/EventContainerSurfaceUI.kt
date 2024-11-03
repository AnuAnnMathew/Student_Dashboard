package com.studentdashboard.screens.home.timetable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

/**
 * A customizable surface to contain event content with rounded corners based on its position in a list.
 *
 * @param modifier Modifier to be applied to the surface.
 * @param isLastItemInDate Boolean indicating if this is the last item for the date.
 * @param isFirstItemInDate Boolean indicating if this is the first item for the date.
 * @param contents Composable function to display the content inside the surface.
 */
@Composable
fun EventContainerSurfaceUI(
    modifier: Modifier = Modifier,
    isLastItemInDate: Boolean,
    isFirstItemInDate: Boolean,
    contents: @Composable () -> Unit,
) {
    val cornerRadius = 16.dp
    val surfaceShape = remember(isLastItemInDate, isFirstItemInDate) {
        when {
            isLastItemInDate && isFirstItemInDate -> RoundedCornerShape(cornerRadius)
            isLastItemInDate -> RoundedCornerShape(
                topEnd = 0.dp,
                topStart = 0.dp,
                bottomStart = cornerRadius,
                bottomEnd = cornerRadius
            )

            isFirstItemInDate -> RoundedCornerShape(
                topEnd = cornerRadius,
                topStart = cornerRadius,
                bottomStart = 0.dp,
                bottomEnd = 0.dp
            )

            else -> RectangleShape
        }
    }

    Surface(modifier = modifier, color = Color.White, shape = surfaceShape) {
        contents()
    }
}
