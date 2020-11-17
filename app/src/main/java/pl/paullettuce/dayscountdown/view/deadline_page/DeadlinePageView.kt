package pl.paullettuce.dayscountdown.view.deadline_page

import pl.paullettuce.dayscountdown.model.ToDoItem
import pl.paullettuce.dayscountdown.notfications.ReminderRepeatInterval

interface DeadlinePageView {
    fun updateDeadlineDate(friendlyDatetime: String)
    fun updateTimeLeft(days: Long, hours: Long, minutes: Long)
    fun updateReminderInterval(reminderRepeatInterval: ReminderRepeatInterval)
    fun updateThingsToDo(list: List<ToDoItem>)

    fun openDeadlineDateTimePicker(initialPickerDatetimeMillis: Long)
}