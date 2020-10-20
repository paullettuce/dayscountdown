package pl.paullettuce.dayscountdown.model

data class DeadlineData(
    var id: Long = 0,
    var datetimeTimestamp: Long = 0,
    var notificationEnabled: Boolean = false,
    var notificationTime: NotificationTime = NotificationTime(0, 0)
//    var thingsToDo: List<ThingToDo>
)