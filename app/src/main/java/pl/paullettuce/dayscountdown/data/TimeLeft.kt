package pl.paullettuce.dayscountdown.data

import pl.paullettuce.dayscountdown.commons.TimeUtil


class TimeLeft(private val milliseconds: Long) {
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
        days = milliseconds / TimeUtil.oneDayAsMillis()
    }

    private fun calculateHours() {
        hours = (milliseconds - TimeUtil.daysToMills(days)) / TimeUtil.oneHourAsMillis()
    }

    private fun calculateMinutes() {
        minutes = (milliseconds - TimeUtil.daysToMills(days) - TimeUtil.hoursToMills(hours)) / TimeUtil.oneMinuteAsMillis()
    }
}