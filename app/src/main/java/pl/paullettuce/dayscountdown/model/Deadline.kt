package pl.paullettuce.dayscountdown.model

import pl.paullettuce.dayscountdown.commons.TimeFormatter
import pl.paullettuce.dayscountdown.notfications.ReminderRepeatInterval

class Deadline {

    private val deadlineData = DeadlineData()

    override fun toString(): String {
        return TimeFormatter.formatMillis(deadlineData.datetimeTimestamp)
    }

    fun getId() = deadlineData.id

    fun setDeadlineDatetime(datetime: Long) {
        deadlineData.datetimeTimestamp = datetime
    }

    fun getDeadlineDatetime(): Long = deadlineData.datetimeTimestamp

    fun setNotificationEnabled(enabled: Boolean) {
        deadlineData.reminderNotificationEnabled = enabled
    }

    fun getNotificationEnabled() = deadlineData.reminderNotificationEnabled

    fun setReminderRepeatInterval(interval: ReminderRepeatInterval) {
        deadlineData.reminderRepeatInterval = interval
    }

    fun getReminderRepeatInterval() = deadlineData.reminderRepeatInterval
}