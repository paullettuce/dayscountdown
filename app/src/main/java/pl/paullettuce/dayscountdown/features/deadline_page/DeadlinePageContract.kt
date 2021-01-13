package pl.paullettuce.dayscountdown.features.deadline_page

import androidx.lifecycle.LiveData
import pl.paullettuce.dayscountdown.data.TimeUnitToPluralRes
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.storage.entity.TodoItem
import pl.paullettuce.dayscountdown.notfications.reminder.ReminderRepeatInterval

interface DeadlinePageContract {
    interface View {
        fun showDeadlineDate(friendlyDatetime: String)
        fun showTimeLeftString(timeLeftString: String)
        fun showReminderIntervalValue(intervalValue: Int)
        fun showReminderTimeUnits(units: List<TimeUnitToPluralRes>, selectItemIndex: Int)

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

        //TodoItems
        fun observeForTodoListItems(): LiveData<List<ViewTypedListItem>>
        fun saveTodoItem(todoText: String)
        fun markAsDone(todoItem: TodoItem)
        fun markAsNotDone(todoItem: TodoItem)
        fun deleteTodoItem(todoItem: TodoItem)
    }
}