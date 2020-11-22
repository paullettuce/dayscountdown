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
import pl.paullettuce.dayscountdown.REMINDER_INTERVAL_MAX_VALUE
import pl.paullettuce.dayscountdown.REMINDER_INTERVAL_MIN_VALUE
import pl.paullettuce.dayscountdown.model.ToDoItem
import pl.paullettuce.dayscountdown.notfications.AppNotificationManagerImpl
import pl.paullettuce.dayscountdown.notfications.ReminderIntervalTimeUnitPluralizingAdapter
import pl.paullettuce.dayscountdown.notfications.ReminderRepeatInterval
import pl.paullettuce.dayscountdown.notfications.TimeUnitToPluralResource
import pl.paullettuce.dayscountdown.presenter.DeadlinePagePresenter
import pl.paullettuce.dayscountdown.view.DateTimePicker
import pl.paullettuce.dayscountdown.view.custom.MinMaxEditText
import pl.paullettuce.dayscountdown.view.todo_list.ToDoAdapter
import java.util.concurrent.TimeUnit

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
        setupReminderIntervalET()
        setAdapter()
        setListeners()
        presenter.initiate()
    }

    val adapterItems = mutableListOf<TimeUnitToPluralResource>()
    var adapter: ReminderIntervalTimeUnitPluralizingAdapter? = null

    private fun setAdapter() {
        adapterItems.add(TimeUnitToPluralResource(context!!, TimeUnit.DAYS, R.plurals.days, reminderIntervalET.getValue()))
        adapterItems.add(TimeUnitToPluralResource(context!!, TimeUnit.HOURS, R.plurals.hours, reminderIntervalET.getValue()))

        adapter = ReminderIntervalTimeUnitPluralizingAdapter(
            context!!,
            adapterItems
        )
        reminderIntervalTimeUnitSpinner.adapter = adapter
    }

    override fun showThingsToDo(list: List<ToDoItem>) {
        val todolist = resources.getStringArray(R.array.mock_things_to_do).map { ToDoItem(it) }
        (thingsToDoRV.adapter as ToDoAdapter).setItems(todolist)
    }

    override fun showDeadlineDate(friendlyDatetime: String) {
        dateTimePickBtn.text = friendlyDatetime
    }

    override fun showReminderInterval(reminderRepeatInterval: ReminderRepeatInterval) {
        setReminderInterval(reminderRepeatInterval)
    }

    override fun openDeadlineDateTimePicker(initialPickerDatetimeMillis: Long) {
        context?.let {
            val dateTimePicker = DateTimePicker(it) { pickedDatetimeMillis ->
                presenter.saveDeadlineDatetime(pickedDatetimeMillis)
            }
            dateTimePicker.pickDateAndTime(initialPickerDatetimeMillis)
        }
    }

    override fun showDaysAndHours(days: Long, hours: Long) {
        val text = getString(R.string.days_hours, days, hours)
        timeLeftTV.text = text
    }

    override fun showHoursAndMinutes(hours: Long, minutes: Long) {
        val text = getString(R.string.hours_minutes, hours, minutes)
        timeLeftTV.text = text
    }

    override fun showMinutes(minutes: Long) {
        val text = getString(R.string.minutes, minutes)
        timeLeftTV.text = text
    }

    private fun saveReminderInterval() {
//        presenter.saveReminderInterval(readReminderInterval)
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


    private fun setupRecyclerView() {
        thingsToDoRV.layoutManager = LinearLayoutManager(this.context)
        thingsToDoRV.adapter = ToDoAdapter(emptyList())
    }

    private fun setListeners() {
        dateTimePickBtn.setOnClickListener {
            presenter.openDeadlineDatetimePicker()
        }
        reminderCheckbox.setOnCheckedChangeListener { _, isChecked ->
            presenter.toggleNotifications(isChecked, 1L)
        }
    }

    private fun setupReminderIntervalET() {
        reminderIntervalET.setValueRange(REMINDER_INTERVAL_MIN_VALUE, REMINDER_INTERVAL_MAX_VALUE)
        reminderIntervalET.onChangeCallback = ReminderIntervalValueChangeCallback()
    }

    inner class ReminderIntervalValueChangeCallback : MinMaxEditText.OnChange {
        override fun onValueChange(number: Int) {
            adapter?.quantity = number
            saveReminderInterval()
        }
    }
}