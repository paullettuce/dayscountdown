package pl.paullettuce.dayscountdown.model

class Deadline {

    private val deadlineData = DeadlineData()

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