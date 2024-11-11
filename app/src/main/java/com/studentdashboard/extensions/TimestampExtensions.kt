/**
 * Extension functions for formatting timestamps and dates in the student dashboard application.
 *
 * This file contains utility functions that extend the `Long` type, enabling the formatting of
 * timestamps to various human-readable date and time representations. These extensions are
 * particularly useful for displaying date and time information in a user-friendly format across
 * different parts of the application.
 */

package com.studentdashboard.extensions

import android.content.Context
import com.studentdashboard.R
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val FORMAT_HH_MM_AM_PM = "h:mma"
private const val FORMAT_TO_NORMAL_DATE = "dd MMMM yyyy"

/**
 * Formats a timestamp in milliseconds to a specified date format.
 *
 * @param format The desired date format string.
 * @return A string representing the formatted date.
 */
private fun Long.formatTimestamp(format: String): String {
    val simpleDateFormat = SimpleDateFormat(format, Locale("en", "IN"))
    return simpleDateFormat.format(this)
}

/**
 * Formats a timestamp in milliseconds to a LocalDateTime representation based on the given pattern.
 *
 * @param pattern The pattern to format the LocalDateTime.
 * @return A string representing the formatted date and time.
 */
private fun Long.formatLocalDateTime(pattern: String): String {
    val instant = Instant.ofEpochMilli(this)
    val ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    return ldt.format(dateTimeFormatter) ?: ""
}

/**
 * Formats a timestamp for display in a list, indicating if the date is today, tomorrow,
 * or a specific day.
 *
 * @param context The context to access string resources.
 * @return A string representing the formatted date, including contextual information.
 */
fun Long.formatLocalDateTimeForList(context: Context): String {
    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
    val now = LocalDateTime.now()

    val dayNameFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM")
    val todayOrTomorrowFormatter = DateTimeFormatter.ofPattern("d MMMM")

    return when {
        dateTime.toLocalDate() == now.toLocalDate() -> "${context.getString(R.string.is_today)}, ${todayOrTomorrowFormatter.format(dateTime)}"
        dateTime.toLocalDate() == now.plusDays(1).toLocalDate() -> "${context.getString(R.string.tomorrow)}, ${todayOrTomorrowFormatter.format(dateTime)}"
        else -> dayNameFormatter.format(dateTime)
    }
}

/**
 * Extension property that formats the timestamp to a string in "h:mma" format.
 */
val Long.timeInsideDayAmPm get() = formatTimestamp(FORMAT_HH_MM_AM_PM)

/**
 * Extension property that formats the timestamp to a normal date string in "dd MMMM yyyy" format.
 */
val Long.normalDateByLocalDateTime get() = formatLocalDateTime(FORMAT_TO_NORMAL_DATE)
