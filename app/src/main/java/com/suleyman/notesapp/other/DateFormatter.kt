package com.suleyman.notesapp.other

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateFormatter {

    @SuppressLint("SimpleDateFormat")
    fun dateFromString(timeInMillis: Long, formatType: FormatType): String {
        val dateFormater = SimpleDateFormat("HH:mm-yyyy")
        val timeFormater = SimpleDateFormat("hh:mm")

        return when(formatType) {
             FormatType.Date -> dateFormater.format(timeInMillis)
             FormatType.Time -> timeFormater.format(timeInMillis)
        }
    }

    enum class FormatType {
        Date,
        Time
    }

}