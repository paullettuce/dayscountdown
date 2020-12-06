package pl.paullettuce.dayscountdown.commons

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import pl.paullettuce.dayscountdown.data.TimeLeft
import java.sql.Timestamp
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

    fun millisToTimestampSinceNow(timestamp: Long): Long {
        val nowMillis = nowMillis()
        return (timestamp - nowMillis).coerceAtLeast(0)
    }

    fun oneDayAsMillis() = 24 * oneHourAsMillis()

    fun oneHourAsMillis() = 60 * oneMinuteAsMillis()

    fun oneMinuteAsMillis() = 60 * 1000

    fun millisToSeconds(millis: Long) = millis / 1000

    fun daysToMills(days: Long) = days * oneDayAsMillis()

    fun hoursToMills(hours: Long) = hours * oneHourAsMillis()
}