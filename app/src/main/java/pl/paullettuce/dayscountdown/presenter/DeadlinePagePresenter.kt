package pl.paullettuce.dayscountdown.presenter

import android.annotation.SuppressLint
import pl.paullettuce.dayscountdown.model.Deadline
import pl.paullettuce.dayscountdown.view.deadline_page.DeadlinePageView
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class DeadlinePagePresenter(
    private val view: DeadlinePageView
) {

    private val deadline = Deadline()

    fun initiate() {
        val initialDatetimeMillis = if (deadline.getDeadlineDatetime() > 0) {
            deadline.getDeadlineDatetime()
        } else {
            nowDatetimeInMillis()
        }

        val displayedDatetime = parseToString(initialDatetimeMillis)
        view.updateDeadlineDate(displayedDatetime)
    }

    fun openDeadlineDatetimePicker() {
        val initialDatetimeMillis = if (deadline.getDeadlineDatetime() > 0) {
            deadline.getDeadlineDatetime()
        } else {
            nowDatetimeInMillis()
        }

        view.openDeadlinePickerWithStartDate(initialDatetimeMillis)
    }

    fun updateDeadlineDatetime(datetimeMillis: Long) {
        deadline.setDeadlineDatetime(datetimeMillis)

        val displayedDatetime = parseToString(datetimeMillis)
        view.updateDeadlineDate(displayedDatetime)
    }


    @Deprecated("Remove from presenter")
    @SuppressLint("NewApi")
    private fun parseToString(datetime: Long): String {
        val date = LocalDateTime.ofEpochSecond(datetime/1000, 0, ZoneOffset.ofHours(2))
        return date.toString()
    }

    private fun nowDatetimeInMillis() = Calendar.getInstance().timeInMillis
}