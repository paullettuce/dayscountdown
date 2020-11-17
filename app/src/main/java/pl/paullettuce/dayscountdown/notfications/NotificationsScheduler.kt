package pl.paullettuce.dayscountdown.notfications

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import pl.paullettuce.dayscountdown.notfications.work.ReminderPeriodicWorkRequest

object NotificationsScheduler {

    private const val REMINDERS_UNIQUE_WORK_NAME = "REMINDERS_UNIQUE_WORK_NAME"

    fun scheduleReminders(context: Context, deadlineId: Long, interval: ReminderRepeatInterval) {
        val request = ReminderPeriodicWorkRequest(deadlineId, interval)
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            createUniqueWorkName(deadlineId),
            ExistingPeriodicWorkPolicy.REPLACE,
            request.build()
        )
    }

    fun disableReminders(context: Context, deadlineId: Long) {
        WorkManager.getInstance(context).cancelUniqueWork(createUniqueWorkName(deadlineId))
    }

    private fun createUniqueWorkName(deadlineId: Long) =
        REMINDERS_UNIQUE_WORK_NAME + "_deadline_id=$deadlineId"
}