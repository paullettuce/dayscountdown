package pl.paullettuce.dayscountdown.features.deadline_page

import pl.paullettuce.dayscountdown.data.TimeUnitToPluralRes
import pl.paullettuce.dayscountdown.storage.entity.ToDoItem
import pl.paullettuce.dayscountdown.notfications.reminder.ReminderRepeatInterval

interface DeadlinePageContract {
    interface View {
        fun showDeadlineDate(friendlyDatetime: String)
        fun showTimeLeftString(timeLeftString: String)
        fun showReminderIntervalValue(intervalValue: Int)
        fun showReminderTimeUnits(units: List<TimeUnitToPluralRes>, selectItemIndex: Int)
        fun showThingsToDo(items: List<ToDoItem>)

        fun openDeadlineDateTimePicker(initialPickerDatetimeMillis: Long)

        //debug only
        fun showMsg(msg: String)
    }

    interface Presenter {
        fun initiate()
        fun openDeadlineDatetimePicker()
        fun saveDeadlineDatetime(datetimeMillis: Long)
        fun saveReminderRepeatInterval(reminderRepeatInterval: ReminderRepeatInterval)
        fun toggleNotifications(enableNotifications: Boolean, timestamp: Long)

        //ToDoItems
        fun saveTodoItem(todoText: String)
        fun markAsDone(toDoItem: ToDoItem)
        fun markAsNotDone(toDoItem: ToDoItem)
        fun deleteTodoItem(toDoItem: ToDoItem)
    }
}