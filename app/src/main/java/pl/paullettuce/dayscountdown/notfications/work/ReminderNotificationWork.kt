package pl.paullettuce.dayscountdown.notfications.work

import android.content.Context
import android.util.Log
import androidx.work.*
import pl.paullettuce.dayscountdown.model.DeadlineData
import pl.paullettuce.dayscountdown.notfications.ReminderNotification
import pl.paullettuce.dayscountdown.notfications.ReminderRepeatInterval

const val DEADLINE_ID_KEY = "DEADLINE_ID_KEY"

class ShowReminderNotificationWork(
    appContext: Context,
    workerParams: WorkerParameters
) :
    Worker(appContext, workerParams) {
    private val TAG = "ShowReminderNotificatio"

    override fun doWork(): Result {
        val deadlineId = inputData.getLong(DEADLINE_ID_KEY, -1)
        if (deadlineId < 0) return Result.failure()

        val deadlineData = readDbForDeadline(deadlineId)
        showNotification(deadlineData)
        return Result.success()
    }

    private fun readDbForDeadline(deadlineId: Long): DeadlineData {
        return DeadlineData()
    }

    private fun showNotification(deadlineData: DeadlineData) {
        Log.d(TAG, "showNotification fired, deadlineData=$deadlineData")
        ReminderNotification().createAndShow(applicationContext, "title", "content")
    }
}