package pl.paullettuce.dayscountdown.notfications.builder

import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.notfications.REMINDER_NOTIFICATIONS_CHANNEL_ID

sealed class NotificationParameters(
    val channelId: String,
    val title: String,
    @DrawableRes val smallIconResId: Int = R.drawable.ic_launcher_foreground,
    val priority: Int = NotificationCompat.PRIORITY_HIGH
)

class ReminderNotificationParams(title: String) :
    NotificationParameters(REMINDER_NOTIFICATIONS_CHANNEL_ID, title)
