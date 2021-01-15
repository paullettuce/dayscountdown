package pl.paullettuce.dayscountdown.storage.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "deadlinedata")
data class DeadlineData(
    var datetimeTimestamp: Long = 0,
    var reminderNotificationEnabled: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @Ignore
    var reminderRepeatInterval: ReminderRepeatInterval = ReminderRepeatInterval.default()
}