package pl.paullettuce.dayscountdown.features.deadline_page

import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.TimeFormatter
import pl.paullettuce.dayscountdown.commons.TimeUtil
import pl.paullettuce.dayscountdown.data.Deadline
import pl.paullettuce.dayscountdown.data.TimeLeft
import pl.paullettuce.dayscountdown.data.TimeUnitToPluralRes
import pl.paullettuce.dayscountdown.notfications.AppNotificationManager
import pl.paullettuce.dayscountdown.notfications.reminder.ReminderRepeatInterval
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DeadlinePagePresenter
@Inject constructor(
    private val view: DeadlinePageContract.View,
    private val notificationManager: AppNotificationManager
) : DeadlinePageContract.Presenter {
    private val deadline = Deadline()
    private val reminderTimeUnits = listOf(
        TimeUnitToPluralRes(TimeUnit.DAYS, R.plurals.days),
        TimeUnitToPluralRes(TimeUnit.HOURS, R.plurals.hours)
    )

    override fun initiate() {
        showDeadlineDate()
        showDaysLeft()
        showReminderInterval()

        // TODO: 15.11.2020 use data from db
        view.showThingsToDo(emptyList())
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
//        notificationManager.scheduleReminders(deadline.getId(), ReminderRepeatInterval.default())
        notificationManager.showInstantNotification(deadline.getData())
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

    private fun showReminderInterval() {
        val interval = deadline.getReminderRepeatInterval()
        view.showReminderIntervalValue(interval.repeatInterval)
        view.showReminderTimeUnits(reminderTimeUnits, selectItemIndex(interval))
    }

    private fun selectItemIndex(interval: ReminderRepeatInterval): Int =
        reminderTimeUnits.indexOfFirst { it.timeUnit == interval.repeatIntervalTimeUnit }
}