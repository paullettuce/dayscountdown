package pl.paullettuce.dayscountdown.notfications

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.view.MainActivity

//Id is needed to update notification instead of duplicating
const val REMINDER_NOTIFICATION_ID = 354511

class ReminderNotification {

    fun createAndShow(context: Context, title: String, content: String) {
        val builder = NotificationCompat.Builder(
            context,
            REMINDER_NOTIFICATIONS_CHANNEL_ID
        )
            .setStyle(NotificationCompat.BigTextStyle())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(content)
            .setContentIntent(openActivityIntent(context))
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        with(NotificationManagerCompat.from(context)) {
            notify(REMINDER_NOTIFICATION_ID, builder.build())
        }
    }

    private fun openActivityIntent(context: Context): PendingIntent? {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(context, 0, intent, 0)
    }
}