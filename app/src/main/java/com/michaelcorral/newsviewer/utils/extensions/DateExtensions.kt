package com.michaelcorral.newsviewer.utils.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.dateInFormat(format: String): Date? {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    var parsedDate: Date? = null
    try {
        parsedDate = dateFormatter.parse(this)
    } catch (ignored: ParseException) {
        ignored.printStackTrace()
    }
    return parsedDate
}

fun Date.today(): String {
    val dateFormatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return dateFormatter.format(this)
}

fun Date.currentTime(is24HourClock: Boolean = true): String {
    val timePattern = if (is24HourClock) "hh:mm:ss" else "hh:mm:ss aa"
    val timeFormatter = SimpleDateFormat(timePattern, Locale.getDefault())
    return timeFormatter.format(this)
}