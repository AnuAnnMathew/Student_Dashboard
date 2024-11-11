package com.studentdashboard.screens.home.timetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.studentdashboard.R
import com.studentdashboard.data.models.TimeTableSubmission
import com.studentdashboard.extensions.timeInsideDayAmPm
import com.studentdashboard.ui.theme.submissionColorNotSubmitted
import com.studentdashboard.ui.theme.submissionColorSubmitted

/**
 * A composable function that displays a submission entry in the timetable, including its submission
 * time, title, and submission status. The date is displayed only for the first item in the date.
 *
 * @param modifier Modifier to be applied to the overall layout of the submission.
 * @param item The TimeTableSubmission containing details of the submission to be displayed.
 * @param isFirstItemInDate A boolean indicating whether this is the first item of the date,
 *                           affecting whether the date is displayed.
 */
@Composable
fun TimeTableSubmissionUI(
    modifier: Modifier = Modifier,
    item: TimeTableSubmission,
    isFirstItemInDate: Boolean
) {
    Column(
        modifier = modifier
    ) {
        // Display the date if this is the first item of the date
        if (isFirstItemInDate) {
            TimeTableDate(
                timestamp = item.submissionTime,
                modifier = Modifier
            )
        }

        // Layout for submission status and time display
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.weight(.35f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display submission icon with color based on submission status
                Icon(
                    painter = painterResource(id = R.drawable.test_icon),
                    contentDescription = null,
                    tint = if (item.isSubmitted) submissionColorSubmitted else submissionColorNotSubmitted
                )

                // Display the formatted submission time
                Text(
                    text = item.submissionTime.timeInsideDayAmPm,
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Layout for event title and submission status message
            Column(
                modifier = Modifier.weight(.65f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                // Display the event title
                EventTitleTextUI(title = item.title)

                // If the submission is closed, display a closed submission message
                if (item.isClosed) {
                    EventTitleTextUI(title = stringResource(id = R.string.submission_closed))
                }

                // Display submission status message
                EventSubtitleUI(
                    text = stringResource(
                        id = if (item.isSubmitted) {
                            R.string.submitted_text
                        } else {
                            R.string.not_submitted_text
                        }
                    )
                )
            }
        }
    }
}
