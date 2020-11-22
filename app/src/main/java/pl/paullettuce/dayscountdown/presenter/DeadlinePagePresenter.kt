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
        showDeadlineDate()
        showDaysLeft()
        view.showReminderInterval(deadline.getReminderRepeatInterval())
        showReminderTimeUnits()

        // TODO: 15.11.2020 use data from db
        view.showThingsToDo(emptyList())
    }

    fun openDeadlineDatetimePicker() {
        val initialDatetimeMillis = getDeadlineDatetimeOrNowIfEmpty()
        view.openDeadlineDateTimePicker(initialDatetimeMillis)
    }

    fun saveDeadlineDatetime(datetimeMillis: Long) {
        deadline.setDeadlineDatetime(datetimeMillis)

        val formattedDatetime = deadline.toString()
        view.showDeadlineDate(formattedDatetime)

        showDaysLeft()
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
        val timeLeft = TimeUtil.timeBetween(deadlineDatetime, TimeUtil.nowMillis())

        when {
            timeLeft.days > 0 -> {
                view.showDaysAndHours(timeLeft.days, timeLeft.hours)
            }
            timeLeft.hours > 0 -> {
                view.showHoursAndMinutes(timeLeft.hours, timeLeft.minutes)
            }
            else -> {
                view.showMinutes(timeLeft.minutes)
            }
        }
    }

    private fun showReminderTimeUnits() {

    }
}