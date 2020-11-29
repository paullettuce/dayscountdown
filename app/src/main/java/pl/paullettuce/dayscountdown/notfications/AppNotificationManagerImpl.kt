package pl.paullettuce.dayscountdown.notfications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val REMINDER_NOTIFICATIONS_CHANNEL_ID = "320-5430958-0"
const val REMINDER_NOTIFICATIONS_CHANNEL_NAME = "Reminders"

class AppNotificationManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context
): AppNotificationManager {
    private val TAG = "AppNotificationManager"

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerNotificationChannel(context)
        }
    }

    override fun scheduleReminders(
        deadlineId: Long,
        interval: ReminderRepeatInterval
    ) {
        Log.d(TAG, "scheduleReminders for deadlineId=$deadlineId, with interval=$interval")
        NotificationsScheduler.scheduleReminders(context, deadlineId, interval)
    }

    override fun disableReminders(deadlineId: Long) {
        Log.d(TAG, "disableReminders for deadlineId=$deadlineId")
        NotificationsScheduler.disableReminders(context, deadlineId)
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