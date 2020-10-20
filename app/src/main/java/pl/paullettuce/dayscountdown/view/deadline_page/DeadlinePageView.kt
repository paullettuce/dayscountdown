package pl.paullettuce.dayscountdown.view.deadline_page

interface DeadlinePageView {
    fun updateDeadlineDate(friendlyDatetime: String)
    fun updateDaysLeft(daysLeft: Int)
    fun updateNotificationTime(time: String)
    fun openDeadlinePickerWithStartDate(initialPickerDatetimeMillis: Long)

//    fun updateThingsToDo(list: List<ThingToDo>)
}