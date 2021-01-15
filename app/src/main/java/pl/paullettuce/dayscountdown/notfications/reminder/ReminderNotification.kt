package pl.paullettuce.dayscountdown.notfications.reminder

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import pl.paullettuce.dayscountdown.MainActivity
import pl.paullettuce.dayscountdown.storage.entity.DeadlineData
import pl.paullettuce.dayscountdown.notfications.builder.NotificationBuildingDirector
import pl.paullettuce.dayscountdown.notfications.builder.ReminderContentFactory
import pl.paullettuce.dayscountdown.notfications.builder.ReminderNotificationParams


class ReminderNotification(
    private val context: Context,
    private val title: String,
    private val deadlineData: DeadlineData
) : NotificationBuildingDirector.IntentProvider {
    // ID is needed to update notification instead of duplicating
    private val REMINDER_NOTIFICATION_ID = 354511
    private val builder =
        NotificationBuildingDirector(
            context,
            ReminderNotificationParams(title),
            this,
            ReminderContentFactory(context, deadlineData)
        )

    fun show() {
        NotificationManagerCompat.from(context)
            .notify(REMINDER_NOTIFICATION_ID, builder.build())
    }

    override fun onClickIntent(): PendingIntent {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, 0)
    }
}