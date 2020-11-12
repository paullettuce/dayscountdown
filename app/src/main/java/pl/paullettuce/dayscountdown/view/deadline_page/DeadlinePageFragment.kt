package pl.paullettuce.dayscountdown.view.deadline_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_deadline_page.*
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.presenter.DeadlinePagePresenter
import pl.paullettuce.dayscountdown.view.DateTimePicker
import java.util.*

class DeadlinePageFragment : Fragment(), DeadlinePageView {
    val presenter = DeadlinePagePresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deadline_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.initiate()

        dateTimePickBtn.setOnClickListener {
            presenter.openDeadlineDatetimePicker()
        }
    }

    override fun updateDeadlineDate(friendlyDatetime: String) {
        dateTimePickBtn.setText(friendlyDatetime)
    }

    //TODO(move logic to presenter)
    override fun updateTimeLeft(days: Long, hours: Long, minutes: Long) {
        when {
            days > 0 -> {
                showDaysAndHours(days, hours)
            }
            hours > 0 -> {
                showHoursAndMinutes(hours, minutes)
            }
            else -> {
                showMinutes(minutes)
            }
        }
    }

    override fun updateNotificationTime(time: String) {
        TODO("Not yet implemented")
    }

    override fun openDeadlinePickerWithStartDate(initialPickerDatetimeMillis: Long) {
        context?.let {
            val dateTimePicker = DateTimePicker(it) { pickedDatetime ->
                onDatePicked(pickedDatetime)
            }

            dateTimePicker.pickDateAndTime(initialPickerDatetimeMillis)
        }
    }

    private fun onDatePicked(datetimeTimestamp: Long) {
        presenter.updateDeadlineDatetime(datetimeTimestamp)
    }

    private fun showDaysAndHours(days: Long, hours: Long) {
        val text = getString(R.string.days_hours, days, hours)
        timeLeftTV.setText(text)
    }

    private fun showHoursAndMinutes(hours: Long, minutes: Long) {
        val text = getString(R.string.hours_minutes, hours, minutes)
        timeLeftTV.setText(text)
    }

    private fun showMinutes(minutes: Long) {
        val text = getString(R.string.minutes, minutes)
        timeLeftTV.setText(text)
    }

}