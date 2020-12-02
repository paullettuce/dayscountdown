package pl.paullettuce.dayscountdown.notfications.reminder

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import pl.paullettuce.dayscountdown.MainActivity
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.data.DeadlineData
import pl.paullettuce.dayscountdown.notfications.REMINDER_NOTIFICATIONS_CHANNEL_ID


class ReminderNotification(
    private val context: Context,
    private val title: String,
    deadlineData: DeadlineData
) {
    // ID is needed to update notification instead of duplicating
    private val REMINDER_NOTIFICATION_ID = 354511
    private val notificationBuilder: NotificationCompat.Builder
    private val contentBuilder = ReminderContentBuilder(context, deadlineData)

    init {
        notificationBuilder = NotificationCompat.Builder(
            context,
            REMINDER_NOTIFICATIONS_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(openActivityIntent(context))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(title)
        addContent()
    }

    fun show() {
        NotificationManagerCompat.from(context)
            .notify(REMINDER_NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun addContent() {
        if (contentBuilder.hasBigTextContent()) {
            setExpandableContent()
        } else {
            setSmallContent()
        }
    }

    private fun setExpandableContent() {
        notificationBuilder.setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(contentBuilder.buildBigTextContent())
        )
    }

    private fun setSmallContent() {
        notificationBuilder.setContentText(contentBuilder.buildSubtitleContent())
    }

    private fun openActivityIntent(context: Context): PendingIntent? {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, 0)
    }
}