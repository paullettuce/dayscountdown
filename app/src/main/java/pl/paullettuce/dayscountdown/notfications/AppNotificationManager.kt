package pl.paullettuce.dayscountdown.notfications

interface AppNotificationManager {
    fun scheduleReminders(deadlineId: Long, interval: ReminderRepeatInterval)
    fun disableReminders(deadlineId: Long)
}