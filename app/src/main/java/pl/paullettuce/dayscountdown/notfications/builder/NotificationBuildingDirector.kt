package pl.paullettuce.dayscountdown.notfications.builder

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat

class NotificationBuildingDirector(
    private val context: Context,
    private val notificationParams: NotificationParameters,
    private val intentProvider: IntentProvider,
    private val notificationContentFactory: NotificationContentFactory
) {
    private val systemNotificationBuilder: NotificationCompat.Builder

    init {
        systemNotificationBuilder = NotificationCompat.Builder(
            context,
            notificationParams.channelId
        )
            .setSmallIcon(notificationParams.smallIconResId)
            .setContentTitle(notificationParams.title)
            .setContentIntent(intentProvider.onClickIntent())
            .setPriority(notificationParams.priority)
        setContent()
    }

    fun build(): Notification = systemNotificationBuilder.build()

    private fun setContent() {
        when (val content = notificationContentFactory.buildContent()) {
            is BigTextContent -> {
                systemNotificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(content.contentText))
            }
            is OneLineContent -> {
                systemNotificationBuilder.setContentText(content.contentText)
            }
        }
    }

    interface IntentProvider {
        fun onClickIntent(): PendingIntent
    }
}







