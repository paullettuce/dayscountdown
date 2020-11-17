package pl.paullettuce.dayscountdown.view.deadline_page

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_deadline_page.*
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.model.ToDoItem
import pl.paullettuce.dayscountdown.notfications.AppNotificationManagerImpl
import pl.paullettuce.dayscountdown.notfications.ReminderRepeatInterval
import pl.paullettuce.dayscountdown.presenter.DeadlinePagePresenter
import pl.paullettuce.dayscountdown.view.DateTimePicker
import pl.paullettuce.dayscountdown.view.todo_list.ToDoAdapter

class DeadlinePageFragment : Fragment(), DeadlinePageView {
    lateinit var presenter: DeadlinePagePresenter
    lateinit var appNotificationManager: AppNotificationManagerImpl

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNotificationManager = AppNotificationManagerImpl(context)
        presenter = DeadlinePagePresenter(this, appNotificationManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deadline_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        presenter.initiate()

        dateTimePickBtn.setOnClickListener {
            presenter.openDeadlineDatetimePicker()
        }

        notificationTimePickBtn.setOnClickListener {
            presenter.openNotificationTimePicker()
        }

        notificationCheckbox.setOnCheckedChangeListener { _, isChecked ->
            presenter.toggleNotifications(isChecked, 1L)
        }
    }

    override fun updateThingsToDo(list: List<ToDoItem>) {
        // TODO: 15.11.2020 quite opposite actually
        if (list.isNotEmpty()) return

        val todolist = resources.getStringArray(R.array.mock_things_to_do).map { ToDoItem(it) }
        (thingsToDoRV.adapter as ToDoAdapter).setItems(todolist)
    }

    override fun updateDeadlineDate(friendlyDatetime: String) {
        dateTimePickBtn.text = friendlyDatetime
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

    override fun updateReminderInterval(reminderRepeatInterval: ReminderRepeatInterval) {
        setReminderInterval(reminderRepeatInterval)
    }

    override fun openDeadlineDateTimePicker(initialPickerDatetimeMillis: Long) {
        context?.let {
            val dateTimePicker = DateTimePicker(it) { pickedDatetimeMillis ->
                presenter.updateDeadlineDatetime(pickedDatetimeMillis)
            }
            dateTimePicker.pickDateAndTime(initialPickerDatetimeMillis)
        }
    }

//    private fun readReminderInterval(): ReminderRepeatInterval {
//        val interval = somefield.gettext.parse
//        val intervalUnit = otherfield.getselected.parse
//        return ReminderRepeatInterval(interval, intervalUnit)
//    }

    private fun setReminderInterval(interval: ReminderRepeatInterval) {
//        somefield.gettext = interval.repeatInterval
//        otherfield.select(interval.repeatIntervalTimeUnit)
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

    private fun setupRecyclerView() {
        thingsToDoRV.layoutManager = LinearLayoutManager(this.context)
        thingsToDoRV.adapter = ToDoAdapter(emptyList())
    }

}