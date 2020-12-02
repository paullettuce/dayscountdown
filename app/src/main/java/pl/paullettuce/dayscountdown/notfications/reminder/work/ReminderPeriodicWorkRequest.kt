package pl.paullettuce.dayscountdown.notfications.reminder.work

import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.workDataOf
import pl.paullettuce.dayscountdown.notfications.reminder.ReminderRepeatInterval
import java.util.concurrent.TimeUnit

class ReminderPeriodicWorkRequest(
    private val deadlineId: Long,
    private val interval: ReminderRepeatInterval
) {
    fun build(): PeriodicWorkRequest {
        return PeriodicWorkRequestBuilder<ShowReminderWork>(
            interval.repeatInterval.toLong(),
            interval.repeatIntervalTimeUnit,
            5, TimeUnit.MINUTES // shortest possible flex time
        )
            .setInputData(
                workDataOf(
                    DEADLINE_ID_KEY to deadlineId
                )
            ).build()
    }
}