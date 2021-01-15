package pl.paullettuce.dayscountdown.storage.entity

import androidx.room.Entity
import androidx.room.TypeConverter
import java.util.concurrent.TimeUnit

@Entity
data class ReminderRepeatInterval(
    val repeatInterval: Int,
    val repeatIntervalTimeUnit: TimeUnit
) {

    override fun toString(): String {
        return "$repeatInterval $repeatIntervalTimeUnit"
    }

    companion object {
        fun default() =
            ReminderRepeatInterval(
                1,
                TimeUnit.HOURS
            )
    }
}

class TimeUnitConverter {
    @TypeConverter
    fun toValue(timeUnit: TimeUnit): String {
        return timeUnit.name
    }

    @TypeConverter
    fun toTimeUnit(name: String): TimeUnit {
        return TimeUnit.valueOf(name)
    }
}