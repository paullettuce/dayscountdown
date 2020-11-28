package pl.paullettuce.dayscountdown.features.deadline_page

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
import pl.paullettuce.dayscountdown.data.TimeUnitToPluralRes
import pl.paullettuce.dayscountdown.data.ToDoItem
import pl.paullettuce.dayscountdown.notfications.AppNotificationManagerImpl
import pl.paullettuce.dayscountdown.notfications.TimeUnitPluralizingAdapter
import pl.paullettuce.dayscountdown.view.DateTimePicker
import pl.paullettuce.dayscountdown.view.MinMaxEditText
import pl.paullettuce.dayscountdown.features.to_do_list.ToDoAdapter

class DeadlinePageFragment : Fragment(), DeadlinePageView {
    lateinit var presenter: DeadlinePagePresenter
    lateinit var appNotificationManager: AppNotificationManagerImpl
    var adapter: TimeUnitPluralizingAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appNotificationManager = AppNotificationManagerImpl(context)
        presenter =
            DeadlinePagePresenter(
                this,
                appNotificationManager
            )
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
        createTimeUnitsAdapter()
        setListeners()
        presenter.initiate()
    }

    override fun showDeadlineDate(friendlyDatetime: String) {
        dateTimePickBtn.text = friendlyDatetime
    }

    override fun openDeadlineDateTimePicker(initialPickerDatetimeMillis: Long) {
        context?.let {
            val dateTimePicker = DateTimePicker(it) { pickedDatetimeMillis ->
                presenter.saveDeadlineDatetime(pickedDatetimeMillis)
            }
            dateTimePicker.pickDateAndTime(initialPickerDatetimeMillis)
        }
    }

    override fun showReminderIntervalValue(intervalValue: Int) {
        reminderIntervalET.setValue(intervalValue)
        adapter?.quantity = intervalValue
    }

    override fun showReminderTimeUnits(units: List<TimeUnitToPluralRes>, selectItemIndex: Int) {
        adapter?.apply {
            clear()
            addAll(units)
        }
        reminderIntervalTimeUnitSpinner.setSelection(selectItemIndex)
    }

    override fun showThingsToDo(list: List<ToDoItem>) {
        val todolist = resources.getStringArray(R.array.mock_things_to_do).map { ToDoItem(it) }
        (thingsToDoRV.adapter as ToDoAdapter).setItems(todolist)
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

    private fun setupRecyclerView() {
        thingsToDoRV.layoutManager = LinearLayoutManager(this.context)
        thingsToDoRV.adapter =
            ToDoAdapter(
                emptyList()
            )
    }

    private fun setListeners() {
        dateTimePickBtn.setOnClickListener {
            presenter.openDeadlineDatetimePicker()
        }
        reminderCheckbox.setOnCheckedChangeListener { _, isChecked ->
            presenter.toggleNotifications(isChecked, 1L)
        }
    }

    private fun createTimeUnitsAdapter() {
        adapter = context?.let {
            TimeUnitPluralizingAdapter(it)
        }
        reminderIntervalTimeUnitSpinner.adapter = adapter
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