package pl.paullettuce.dayscountdown.notfications

import java.util.concurrent.TimeUnit

data class ReminderRepeatInterval(
    val repeatInterval: Int,
    val repeatIntervalTimeUnit: TimeUnit
) {
    override fun toString(): String {
        return "$repeatInterval $repeatIntervalTimeUnit"
    }

    companion object SimpleFactory {
        fun default() = ReminderRepeatInterval(131, TimeUnit.HOURS)
    }
}