package pl.paullettuce.dayscountdown.notfications.reminder.work

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.data.DeadlineData
import pl.paullettuce.dayscountdown.notfications.reminder.ReminderNotification

const val DEADLINE_ID_KEY = "DEADLINE_ID_KEY"

class ShowReminderWork(
    appContext: Context,
    workerParams: WorkerParameters
) : Worker(appContext, workerParams) {
    private val TAG = "ShowReminderNotificatio"

    override fun doWork(): Result {
        checkIfPastDeadline()
        val deadlineId = inputData.getLong(DEADLINE_ID_KEY, -1)
        if (deadlineId < 0) return Result.failure()

        val deadlineData = readDbForDeadline(deadlineId)
        showNotification(deadlineData)
        return Result.success()
    }

    private fun checkIfPastDeadline() {
//        if (pastDeadline) {
//            showDeadlineTimeoutNotification() = reschedule button
//            unschedulePeriodicWork()
//        }
    }

    private fun readDbForDeadline(deadlineId: Long): DeadlineData {
        return DeadlineData()
    }

    private fun showNotification(deadline: DeadlineData) = with(applicationContext) {
        Log.d(TAG, "showNotification fired, deadlineData=$deadline")

        ReminderNotification(
            this,
            getString(R.string.deadline_reminder_title),
            deadline
        ).show()
    }
}