package pl.paullettuce.dayscountdown.commons

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import pl.paullettuce.dayscountdown.model.TimeLeft
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.*

object TimeUtil {
    private const val TAG = "TimeUtil"

    fun nowMillis() = Calendar.getInstance().timeInMillis

    @RequiresApi(Build.VERSION_CODES.O)
    fun getLocalZoneOffset(): ZoneOffset {
        val odt: OffsetDateTime = OffsetDateTime.now(ZoneId.systemDefault())
        return odt.offset
    }

    fun timeBetween(datetime1: Long, datetime2: Long): TimeLeft {
        Log.d(TAG, "timeBetween, time1: $datetime1, time2: $datetime2")
        if (datetime1 == 0L || datetime2 == 0L) return TimeLeft(0)
        val millisBetween = if (datetime1 > datetime2) {
            datetime1 - datetime2
        } else {
            datetime2 - datetime1
        }
        return TimeLeft(millisBetween)
    }

    fun oneDayAsMillis() = 24 * oneHourAsMillis()

    fun oneHourAsMillis() = 60 * oneMinuteAsMillis()

    fun oneMinuteAsMillis() = 60 * 1000

    fun millisToSeconds(millis: Long) = millis / 1000

    fun daysToMills(days: Long) = days * oneDayAsMillis()

    fun hoursToMills(hours: Long) = hours * oneHourAsMillis()
}