package pl.paullettuce.dayscountdown.data

import pl.paullettuce.dayscountdown.commons.TimeUtil


class TimeLeft
private constructor(private val millisLeft: Long) {
    var days: Long = 0
        private set
    var hours: Long = 0
        private set
    var minutes: Long = 0
        private set

    init {
        transformMillisToFriendlyUnits()
    }

    private fun transformMillisToFriendlyUnits() {
        calculateDays()
        calculateHours()
        calculateMinutes()
    }

    private fun calculateDays() {
        days = millisLeft / TimeUtil.oneDayAsMillis()
    }

    private fun calculateHours() {
        hours = (millisLeft - TimeUtil.daysToMills(days)) / TimeUtil.oneHourAsMillis()
    }

    private fun calculateMinutes() {
        minutes = (millisLeft - TimeUtil.daysToMills(days) - TimeUtil.hoursToMills(hours)) / TimeUtil.oneMinuteAsMillis()
    }

    companion object {
        fun betweenNowAndTimestamp(timestamp: Long): TimeLeft {
            val millisLeft = TimeUtil.millisToTimestampSinceNow(timestamp)
            return TimeLeft(millisLeft)
        }
    }
}