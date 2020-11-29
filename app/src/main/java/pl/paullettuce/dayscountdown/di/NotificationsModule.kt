package pl.paullettuce.dayscountdown.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.paullettuce.dayscountdown.notfications.AppNotificationManager
import pl.paullettuce.dayscountdown.notfications.AppNotificationManagerImpl

@Module
@InstallIn(ApplicationComponent::class)
abstract class NotificationsModule {

    @Binds
    abstract fun bindAppNotificationManager(
        notificationManagerImpl: AppNotificationManagerImpl
    ): AppNotificationManager
}