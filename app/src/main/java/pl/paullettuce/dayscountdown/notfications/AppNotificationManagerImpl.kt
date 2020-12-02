package pl.paullettuce.dayscountdown.notfications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.extensions.logd
import pl.paullettuce.dayscountdown.data.DeadlineData
import pl.paullettuce.dayscountdown.notfications.reminder.ReminderNotification
import pl.paullettuce.dayscountdown.notfications.reminder.ReminderRepeatInterval
import javax.inject.Inject

const val REMINDER_NOTIFICATIONS_CHANNEL_ID = "320-5430958-0"
const val REMINDER_NOTIFICATIONS_CHANNEL_NAME = "Reminders"

class AppNotificationManagerImpl
@Inject constructor(
    @ApplicationContext private val context: Context
) : AppNotificationManager {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerNotificationChannel(context)
        }
    }

    override fun scheduleReminders(
        deadlineId: Long,
        interval: ReminderRepeatInterval
    ) {
        logd("scheduleReminders for deadlineId=$deadlineId, with interval=$interval")
        NotificationsScheduler.scheduleReminders(context, deadlineId, interval)
    }

    override fun disableReminders(deadlineId: Long) {
        logd("disableReminders for deadlineId=$deadlineId")
        NotificationsScheduler.disableReminders(context, deadlineId)
    }

    override fun showInstantNotification(deadlineData: DeadlineData) = with(context) {
        ReminderNotification(
            this,
            getString(R.string.deadline_reminder_title),
            deadlineData
        ).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registerNotificationChannel(context: Context?) {
        val notificationChannel = NotificationChannel(
            REMINDER_NOTIFICATIONS_CHANNEL_ID,
            REMINDER_NOTIFICATIONS_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemNotificationManager(context)
        notificationManager?.createNotificationChannel(notificationChannel)
    }

    private fun getSystemNotificationManager(context: Context?) =
        context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
}