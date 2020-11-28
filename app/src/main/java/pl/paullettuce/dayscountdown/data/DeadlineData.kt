package pl.paullettuce.dayscountdown.data

import pl.paullettuce.dayscountdown.notfications.ReminderRepeatInterval

data class DeadlineData(
    var id: Long = 0,
    var datetimeTimestamp: Long = 0,
    var reminderNotificationEnabled: Boolean = false,
    var reminderRepeatInterval: ReminderRepeatInterval = ReminderRepeatInterval.default(),
    var thingsToDo: List<ToDoItem> = emptyList()
)