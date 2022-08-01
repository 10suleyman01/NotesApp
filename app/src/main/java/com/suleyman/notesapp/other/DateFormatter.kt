package com.suleyman.notesapp.other

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    private var dateFormat = SimpleDateFormat("HH:mm-yyyy", Locale.ROOT)
    private val timeFormat = SimpleDateFormat("hh:mm", Locale.ROOT)

    @SuppressLint("SimpleDateFormat")
    fun dateFromString(timeInMillis: Long, formatType: FormatType): String {
        return when (formatType) {
            FormatType.Date -> dateFormat.format(timeInMillis)
            FormatType.Time -> timeFormat.format(timeInMillis)
        }
    }

    enum class FormatType {
        Date,
        Time
    }

}