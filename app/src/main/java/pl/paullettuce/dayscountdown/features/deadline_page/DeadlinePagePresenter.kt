package pl.paullettuce.dayscountdown.features.deadline_page

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.TimeFormatter
import pl.paullettuce.dayscountdown.commons.TimeUtil
import pl.paullettuce.dayscountdown.data.Deadline
import pl.paullettuce.dayscountdown.data.TimeLeft
import pl.paullettuce.dayscountdown.data.TimeUnitToPluralRes
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.domain.usecase.*
import pl.paullettuce.dayscountdown.notfications.AppNotificationManager
import pl.paullettuce.dayscountdown.notfications.reminder.ReminderRepeatInterval
import pl.paullettuce.dayscountdown.storage.entity.TodoItem
import pl.paullettuce.dayscountdown.view.adapters.TimeLeftStringBuilder
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DeadlinePagePresenter
@Inject constructor(
    private val view: DeadlinePageContract.View,
    private val notificationManager: AppNotificationManager,
    private val timeLeftStringBuilder: TimeLeftStringBuilder,
    private val getTodoListItemsUseCase: GetTodoListItemsUseCase,
    private val saveTodoItemUseCase: SaveTodoItemUseCase,
    private val markTodoItemAsDoneUseCase: MarkTodoItemAsDoneUseCase,
    private val markTodoItemAsNotDoneUseCase: MarkTodoItemAsNotDoneUseCase,
    private val deleteTodoItemUseCase: DeleteTodoItemUseCase
) : DeadlinePageContract.Presenter {
    private val compositeDisposable = CompositeDisposable()
    private val deadline = Deadline()
    private val reminderTimeUnits = listOf(
        TimeUnitToPluralRes(TimeUnit.DAYS, R.plurals.days),
        TimeUnitToPluralRes(TimeUnit.HOURS, R.plurals.hours)
    )

    override fun initiate() {
        showDeadlineDate()
        showDaysLeft()
        showReminderInterval()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    override fun openDeadlineDatetimePicker() {
        val initialDatetimeMillis = getDeadlineDatetimeOrNowIfEmpty()
        view.openDeadlineDateTimePicker(initialDatetimeMillis)
    }

    override fun saveDeadlineDatetime(datetimeMillis: Long) {
        deadline.setDeadlineDatetime(datetimeMillis)

        val formattedDatetime = deadline.toString()
        view.showDeadlineDate(formattedDatetime)

        showDaysLeft()
    }

    //TodoItems section
    override fun observeForTodoListItems(): LiveData<List<ViewTypedListItem>> {
        return getTodoListItemsUseCase()
    }

    override fun saveTodoItem(todoText: String) {
        saveTodoItemUseCase(todoText)
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun markAsDone(todoItem: TodoItem) {
        markTodoItemAsDoneUseCase(todoItem)
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun markAsNotDone(todoItem: TodoItem) {
        markTodoItemAsNotDoneUseCase(todoItem)
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun deleteTodoItem(todoItem: TodoItem) {
        deleteTodoItemUseCase(todoItem)
            .subscribe()
            .addTo(compositeDisposable)
    }

    //Notifications section
    override fun saveReminderRepeatInterval(reminderRepeatInterval: ReminderRepeatInterval) {
        deadline.setReminderRepeatInterval(reminderRepeatInterval)
    }

    override fun toggleNotifications(enableNotifications: Boolean, timestamp: Long) {
        if (enableNotifications) {
            scheduleNotifications()
        } else {
            disableNotifications()
        }
    }

    private fun scheduleNotifications() {
        notificationManager.scheduleReminders(
            deadline.getId(),
            deadline.getReminderRepeatInterval()
        )
    }

    private fun disableNotifications() {
        notificationManager.disableReminders(deadline.getId())
    }

    private fun showDeadlineDate() {
        val initialDatetimeMillis = getDeadlineDatetimeOrNowIfEmpty()
        val formattedDatetime = TimeFormatter.formatMillis(initialDatetimeMillis)
        view.showDeadlineDate(formattedDatetime)
    }

    private fun getDeadlineDatetimeOrNowIfEmpty(): Long {
        return if (deadline.getDeadlineDatetime() > 0) {
            deadline.getDeadlineDatetime()
        } else {
            TimeUtil.nowMillis()
        }
    }

    private fun showDaysLeft() {
        val deadlineDatetime = deadline.getDeadlineDatetime()
        val timeLeft = TimeLeft.betweenNowAndTimestamp(deadlineDatetime)
        view.showTimeLeftString(timeLeftStringBuilder.toPluralString(timeLeft))
    }

    private fun showReminderInterval() {
        val interval = deadline.getReminderRepeatInterval()
        view.showReminderIntervalValue(interval.repeatInterval)
        view.showReminderTimeUnits(reminderTimeUnits, selectItemIndex(interval))
    }

    private fun selectItemIndex(interval: ReminderRepeatInterval): Int =
        reminderTimeUnits.indexOfFirst { it.timeUnit == interval.repeatIntervalTimeUnit }
}