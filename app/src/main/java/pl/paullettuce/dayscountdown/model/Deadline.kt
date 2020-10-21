package pl.paullettuce.dayscountdown.model

import pl.paullettuce.dayscountdown.commons.TimeFormatter

class Deadline {

    private val deadlineData = DeadlineData()

    override fun toString(): String {
        return TimeFormatter.formatMillis(deadlineData.datetimeTimestamp)
    }

    fun setDeadlineDatetime(datetime: Long) {
        deadlineData.datetimeTimestamp = datetime
    }

    fun getDeadlineDatetime(): Long = deadlineData.datetimeTimestamp

    fun setNotificationEnabled(enabled: Boolean) {
        deadlineData.notificationEnabled = enabled
    }

    fun getNotificationEnabled() = deadlineData.notificationEnabled

    fun setNotificationTime(notificationTime: NotificationTime) {
        deadlineData.notificationTime = notificationTime
    }

    fun getNotificationTime() = deadlineData.notificationTime
}