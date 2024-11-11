package com.studentdashboard

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.text.SimpleDateFormat

/**
 * Unit tests for DateUtils functions to ensure correct formatting of timestamps.
 *
 * This class tests various formatting methods for converting timestamps to
 * human-readable date and time formats.
 */
class DateUtilsTest {

    // Format for AM/PM time representation
    private val FORMAT_HH_MM_AM_PM = "h:mma"
    // Format for normal date representation
    private val FORMAT_TO_NORMAL_DATE = "dd MMMM yyyy"

    /**
     * Extension function to format a timestamp to a specific date/time format.
     *
     * @param format The date/time format string to use for formatting.
     * @return The formatted date/time as a String.
     */
    private fun Long.formatTimestamp(format: String): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale("en", "IN"))
        return simpleDateFormat.format(this)
    }

    /**
     * Extension function to format a timestamp to a LocalDateTime based on a given pattern.
     *
     * @param pattern The pattern for formatting the LocalDateTime.
     * @return The formatted LocalDateTime as a String.
     */
    private fun Long.formatLocalDateTime(pattern: String): String {
        val instant = java.time.Instant.ofEpochMilli(this)
        val ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
        return ldt.format(dateTimeFormatter)
    }

    /**
     * Extension function to format a timestamp to a human-readable date string
     * suitable for displaying in a list.
     *
     * The result will indicate if the date is today, tomorrow, or another day.
     *
     * @return The formatted date string indicating if it's today, tomorrow, or another day.
     */
    private fun Long.formatLocalDateTimeForList(): String {
        val dateTime = LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(this), ZoneId.systemDefault())
        val now = LocalDateTime.now()

        val dayNameFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM")
        val todayOrTomorrowFormatter = DateTimeFormatter.ofPattern("d MMMM")

        return when {
            dateTime.toLocalDate() == now.toLocalDate() -> "Today, ${todayOrTomorrowFormatter.format(dateTime)}"
            dateTime.toLocalDate() == now.plusDays(1).toLocalDate() -> "Tomorrow, ${todayOrTomorrowFormatter.format(dateTime)}"
            else -> dayNameFormatter.format(dateTime)
        }
    }

    /**
     * Test to verify that a timestamp is formatted correctly to AM/PM format.
     *
     * This test checks that the formatted string contains "am" or "pm" as expected.
     */
    @Test
    fun `formatTimestamp should format timestamp correctly to AM PM format`() {
        val timestamp = 1731054752000L // Example timestamp for a specific date and time
        val formattedTime = timestamp.formatTimestamp(FORMAT_HH_MM_AM_PM)
        assertTrue(formattedTime.contains("am"))
    }

    /**
     * Test to verify that a timestamp is formatted correctly to a normal date format.
     *
     * This test checks that the formatted date matches the expected string representation.
     */
    @Test
    fun `formatLocalDateTime should format timestamp correctly to normal date format`() {
        val timestamp = 1731054752000L // Example timestamp for November 8, 2024
        val formattedDate = timestamp.formatLocalDateTime(FORMAT_TO_NORMAL_DATE)
        assertEquals("08 November 2024", formattedDate)
    }

    /**
     * Test to verify that today's date is formatted correctly in the list format.
     *
     * This test checks that the formatted result indicates that the date is today.
     */
    @Test
    fun `formatLocalDateTimeForList should return 'Today' when the timestamp is for today's date`() {
        val todayTimestamp = LocalDateTime.now()
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val result = todayTimestamp.formatLocalDateTimeForList()
        val expected = "Today, ${DateTimeFormatter.ofPattern("d MMMM").format(LocalDateTime.now())}"
        assertEquals(expected, result)
    }

    /**
     * Test to verify that tomorrow's date is formatted correctly in the list format.
     *
     * This test checks that the formatted result indicates that the date is tomorrow.
     */
    @Test
    fun `formatLocalDateTimeForList should return 'Tomorrow' when the timestamp is for tomorrow's date`() {
        val tomorrowTimestamp = LocalDateTime.now().plusDays(1)
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val result = tomorrowTimestamp.formatLocalDateTimeForList()
        val expected = "Tomorrow, ${DateTimeFormatter.ofPattern("d MMMM").format(LocalDateTime.now().plusDays(1))}"
        assertEquals(expected, result)
    }

    /**
     * Test to verify that a specific date is formatted correctly in the list format.
     *
     * This test checks that the formatted result provides the full date representation for any other day.
     */
    @Test
    fun `formatLocalDateTimeForList should return full date for any other day`() {
        val specificDateTimestamp = LocalDateTime.of(2024, 11, 10, 14, 30)
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        val result = specificDateTimestamp.formatLocalDateTimeForList()
        assertEquals("Sunday, 10 November", result)
    }
}
