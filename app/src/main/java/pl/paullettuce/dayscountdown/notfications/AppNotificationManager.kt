package pl.paullettuce.dayscountdown.notfications

import pl.paullettuce.dayscountdown.storage.entity.DeadlineData
import pl.paullettuce.dayscountdown.storage.entity.ReminderRepeatInterval

interface AppNotificationManager {
    fun scheduleReminders(deadlineId: Long, interval: ReminderRepeatInterval)
    fun disableReminders(deadlineId: Long)

    fun showInstantNotification(deadlineData: DeadlineData)
}