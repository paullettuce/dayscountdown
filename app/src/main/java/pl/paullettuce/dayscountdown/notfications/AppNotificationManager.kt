package pl.paullettuce.dayscountdown.notfications

import pl.paullettuce.dayscountdown.data.DeadlineData
import pl.paullettuce.dayscountdown.notfications.reminder.ReminderRepeatInterval

interface AppNotificationManager {
    fun scheduleReminders(deadlineId: Long, interval: ReminderRepeatInterval)
    fun disableReminders(deadlineId: Long)

    fun showInstantNotification(deadlineData: DeadlineData)
}