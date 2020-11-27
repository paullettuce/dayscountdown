package pl.paullettuce.dayscountdown.notfications.work

import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.workDataOf
import pl.paullettuce.dayscountdown.notfications.ReminderRepeatInterval
import java.util.concurrent.TimeUnit

class ReminderPeriodicWorkRequest(
    private val deadlineId: Long,
    private val interval: ReminderRepeatInterval
) {
    fun build(): PeriodicWorkRequest {
        return PeriodicWorkRequestBuilder<ShowReminderNotificationWork>(
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