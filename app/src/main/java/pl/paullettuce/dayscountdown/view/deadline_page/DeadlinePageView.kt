package pl.paullettuce.dayscountdown.view.deadline_page

import pl.paullettuce.dayscountdown.model.TimeUnitToPluralRes
import pl.paullettuce.dayscountdown.model.ToDoItem

interface DeadlinePageView {
    fun showDeadlineDate(friendlyDatetime: String)
    fun showDaysAndHours(days: Long, hours: Long)
    fun showHoursAndMinutes(hours: Long, minutes: Long)
    fun showMinutes(minutes: Long)
    fun showReminderIntervalValue(intervalValue: Int)
    fun showReminderTimeUnits(units: List<TimeUnitToPluralRes>, selectItemIndex: Int)
    fun showThingsToDo(list: List<ToDoItem>)

    fun openDeadlineDateTimePicker(initialPickerDatetimeMillis: Long)
}