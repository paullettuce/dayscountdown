package pl.paullettuce.dayscountdown.view.deadline_page

import kotlinx.android.synthetic.main.fragment_deadline_page.*
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.model.ToDoItem
import pl.paullettuce.dayscountdown.notfications.ReminderRepeatInterval

interface DeadlinePageView {
    fun showDeadlineDate(friendlyDatetime: String)
    fun showDaysAndHours(days: Long, hours: Long)
    fun showHoursAndMinutes(hours: Long, minutes: Long)
    fun showMinutes(minutes: Long)
    fun showReminderInterval(reminderRepeatInterval: ReminderRepeatInterval)
    fun showThingsToDo(list: List<ToDoItem>)

    fun openDeadlineDateTimePicker(initialPickerDatetimeMillis: Long)
}