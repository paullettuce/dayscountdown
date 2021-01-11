package pl.paullettuce.dayscountdown.data

import pl.paullettuce.dayscountdown.notfications.reminder.ReminderRepeatInterval
import pl.paullettuce.dayscountdown.storage.entity.ToDoItem

data class DeadlineData(
    var id: Long = 0,
    var datetimeTimestamp: Long = 0,
    var reminderNotificationEnabled: Boolean = false,
    var reminderRepeatInterval: ReminderRepeatInterval = ReminderRepeatInterval.default(),
    var thingsToDo: List<ToDoItem> = listOf()
)