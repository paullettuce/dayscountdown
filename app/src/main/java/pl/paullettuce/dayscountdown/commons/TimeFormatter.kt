package pl.paullettuce.dayscountdown.commons

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

const val TIME_FORMAT ="dd-MM-yyyy HH:mm"
const val TAG = "TimeFormatter"
object TimeFormatter {

    fun formatMillis(datetime: Long): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formatUsingNewApi(datetime)
        } else {
            format(datetime)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun formatUsingNewApi(datetime: Long): String {
        Log.d(TAG, "formatUsingNewApi")
        val date = LocalDateTime.ofEpochSecond(datetime/1000, 0, ZoneOffset.UTC)
        val formatter = DateTimeFormatter.ofPattern(TIME_FORMAT)
        return date.format(formatter)
    }

    private fun format(datetime: Long): String {
        Log.d(TAG, "formatUsingOldApi")
        val formatter = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())
        val date = Date()
        date.time = datetime
        return formatter.format(date)
    }
}