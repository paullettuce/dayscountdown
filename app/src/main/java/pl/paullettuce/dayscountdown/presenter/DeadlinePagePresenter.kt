package pl.paullettuce.dayscountdown.presenter

import pl.paullettuce.dayscountdown.commons.TimeFormatter
import pl.paullettuce.dayscountdown.commons.TimeUtil
import pl.paullettuce.dayscountdown.model.Deadline
import pl.paullettuce.dayscountdown.view.deadline_page.DeadlinePageView

class DeadlinePagePresenter(
    private val view: DeadlinePageView
) {
    private val deadline = Deadline()

    fun initiate() {
        val initialDatetimeMillis = getDeadlineDatetimeOrNowIfEmpty()
        val formattedDatetime = TimeFormatter.formatMillis(initialDatetimeMillis)
        view.updateDeadlineDate(formattedDatetime)
        updateDaysLeft()
    }

    fun openDeadlineDatetimePicker() {
        val initialDatetimeMillis = getDeadlineDatetimeOrNowIfEmpty()
        view.openDeadlinePickerWithStartDate(initialDatetimeMillis)
    }

    fun updateDeadlineDatetime(datetimeMillis: Long) {
        deadline.setDeadlineDatetime(datetimeMillis)

        val formattedDatetime = deadline.toString()
        view.updateDeadlineDate(formattedDatetime)

        updateDaysLeft()
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