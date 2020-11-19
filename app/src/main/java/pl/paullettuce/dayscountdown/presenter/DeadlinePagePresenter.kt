package pl.paullettuce.dayscountdown.presenter

import pl.paullettuce.dayscountdown.commons.TimeFormatter
import pl.paullettuce.dayscountdown.commons.TimeUtil
import pl.paullettuce.dayscountdown.model.Deadline
import pl.paullettuce.dayscountdown.notfications.AppNotificationManager
import pl.paullettuce.dayscountdown.notfications.ReminderRepeatInterval
import pl.paullettuce.dayscountdown.view.deadline_page.DeadlinePageView

class DeadlinePagePresenter(
    private val view: DeadlinePageView,
    private val notificationManager: AppNotificationManager
) {
    private val deadline = Deadline()

    fun initiate() {
        val initialDatetimeMillis = getDeadlineDatetimeOrNowIfEmpty()
        val formattedDatetime = TimeFormatter.formatMillis(initialDatetimeMillis)
        view.updateDeadlineDate(formattedDatetime)
        updateDaysLeft()
        view.updateReminderInterval(deadline.getReminderRepeatInterval())

        // TODO: 15.11.2020 use data from db
        view.updateThingsToDo(emptyList())
    }

    fun openDeadlineDatetimePicker() {
        val initialDatetimeMillis = getDeadlineDatetimeOrNowIfEmpty()
        view.openDeadlineDateTimePicker(initialDatetimeMillis)
    }

    fun openNotificationTimePicker() {

    }

    fun updateDeadlineDatetime(datetimeMillis: Long) {
        deadline.setDeadlineDatetime(datetimeMillis)

        val formattedDatetime = deadline.toString()
        view.updateDeadlineDate(formattedDatetime)

        updateDaysLeft()
    }

    fun saveReminderRepeatInterval(reminderRepeatInterval: ReminderRepeatInterval) {
        deadline.setReminderRepeatInterval(reminderRepeatInterval)
    }

    fun toggleNotifications(enableNotifications: Boolean, timestamp: Long) {
        if (enableNotifications) {
            scheduleNotifications()
        } else {
            disableNotifications()
        }
    }

    private fun scheduleNotifications() {
        notificationManager.scheduleReminders(deadline.getId(), ReminderRepeatInterval.default())
    }

    private fun disableNotifications() {
        notificationManager.disableReminders(deadline.getId())
    }

    private fun getDeadlineDatetimeOrNowIfEmpty(): Long {
        return if (deadline.getDeadlineDatetime() > 0) {
            deadline.getDeadlineDatetime()
        } else {
            TimeUtil.nowMillis()
        }
    }

    private fun updateDaysLeft() {
        val deadlineDatetime = deadline.getDeadlineDatetime()
        val timeLeft = TimeUtil.timeBetween(deadlineDatetime, TimeUtil.nowMillis())

        view.updateTimeLeft(timeLeft.days, timeLeft.hours, timeLeft.minutes)
    }
}