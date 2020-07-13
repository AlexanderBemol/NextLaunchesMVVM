package com.nordokod.nextlaunches.model.source.converters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class Converters {
    @TypeConverter
    fun toDate(dateString: String) : Date? {
        return SimpleDateFormat("MMMMM dd, yyyy HH:mm:ss zzz",Locale.ENGLISH).parse(dateString)
    }
    @TypeConverter
    fun fromDate(date : Date) : String {
        return SimpleDateFormat("MMMMM dd, yyyy HH:mm:ss zzz",Locale.ENGLISH).format(date)
    }

}