package pl.paullettuce.dayscountdown

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import pl.paullettuce.dayscountdown.view.MainActivity

const val REMINDER_NOTIFICATIONS_CHANNEL_ID = "320-5430958-0"
const val REMINDER_NOTIFICATIONS_CHANNEL_NAME = "Reminders"

//since there is only one type of notification, and I want to update it instead of duplicating
const val REMINDER_NOTIFICATION_ID = 354511

class AppNotificationManager(private val context: Context) {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerNotificationChannel()
        }
    }

    companion object {
        // for one type of notification, that method should be enough
        fun showReminderNotification(context: Context, title: String, content: String) {
            val builder = NotificationCompat.Builder(context, REMINDER_NOTIFICATIONS_CHANNEL_ID)
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun registerNotificationChannel() {
        val notificationChannel = NotificationChannel(
            REMINDER_NOTIFICATIONS_CHANNEL_ID,
            REMINDER_NOTIFICATIONS_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificationManager = getSystemNotificationManager()
        notificationManager.createNotificationChannel(notificationChannel)
    }

    private fun getSystemNotificationManager() =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}