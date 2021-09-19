package com.test.app.movies.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    fun changeServerDateToLocalDate(time: String?): String {
        if (time == null || time == "null" || time.isEmpty()) return ""
        var convertedTime = ""
        val shortFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'") //0000-01-01T09:05:00Z

        shortFormat.timeZone = TimeZone.getTimeZone("UTC") //2018-07-30T20:45:38.155645Z


        val longFormat: DateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS") //0000-01-01T09:05:00Z

        longFormat.timeZone = TimeZone.getTimeZone("UTC") //2018-07-30T20:45:38.155645Z


        var date: Date? = null
        try {
            date = shortFormat.parse(time)
        } catch (e: ParseException) {
        }

        if (date == null) {
            try {
                date = longFormat.parse(time)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        try {
            val dateFormat: DateFormat = SimpleDateFormat("dd MMMM, yyyy")
            convertedTime = dateFormat.format(date)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return convertedTime
    }


    fun changeToServerTimeFormat(dateStr: String?): String? {
        if (dateStr == null || dateStr == "null" || dateStr.isEmpty()) return ""
        var convertedTime = ""
        val shortFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd")
        shortFormat.timeZone = TimeZone.getTimeZone("UTC")

        var date: Date? = null
        try {
            date = shortFormat.parse(dateStr)
        } catch (e: ParseException) {
            e.printStackTrace();
        }

        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        convertedTime = dateFormat.format(date)
        return convertedTime
    }

}