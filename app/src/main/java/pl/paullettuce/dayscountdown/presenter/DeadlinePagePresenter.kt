package pl.paullettuce.dayscountdown.presenter

import pl.paullettuce.dayscountdown.commons.TimeFormatter
import pl.paullettuce.dayscountdown.model.Deadline
import pl.paullettuce.dayscountdown.view.deadline_page.DeadlinePageView
import java.util.*

class DeadlinePagePresenter(
    private val view: DeadlinePageView
) {
    private val deadline = Deadline()

    fun initiate() {
        val initialDatetimeMillis = getDeadlineDatetimeOrNowIfEmpty()
        val formattedDatetime = TimeFormatter.formatMillis(initialDatetimeMillis)
        view.updateDeadlineDate(formattedDatetime)
    }

    fun openDeadlineDatetimePicker() {
        val initialDatetimeMillis = getDeadlineDatetimeOrNowIfEmpty()
        view.openDeadlinePickerWithStartDate(initialDatetimeMillis)
    }

    fun updateDeadlineDatetime(datetimeMillis: Long) {
        deadline.setDeadlineDatetime(datetimeMillis)

        val formattedDatetime = deadline.toString()
        view.updateDeadlineDate(formattedDatetime)
    }

    private fun getDeadlineDatetimeOrNowIfEmpty(): Long {
        return if (deadline.getDeadlineDatetime() > 0) {
            deadline.getDeadlineDatetime()
        } else {
            Calendar.getInstance().timeInMillis
        }
    }
}