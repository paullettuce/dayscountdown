package pl.paullettuce.dayscountdown.view.deadline_page

interface DeadlinePageView {
    fun updateDeadlineDate(friendlyDatetime: String)
    fun updateTimeLeft(days: Long, hours: Long, minutes: Long)
    fun updateNotificationTime(time: String)
    fun openDeadlinePickerWithStartDate(initialPickerDatetimeMillis: Long)

//    fun updateThingsToDo(list: List<ThingToDo>)
}