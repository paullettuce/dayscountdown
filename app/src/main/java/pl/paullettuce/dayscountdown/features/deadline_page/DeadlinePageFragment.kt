package pl.paullettuce.dayscountdown.features.deadline_page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_deadline_page.*
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.REMINDER_INTERVAL_MAX_VALUE
import pl.paullettuce.dayscountdown.REMINDER_INTERVAL_MIN_VALUE
import pl.paullettuce.dayscountdown.data.TimeUnitToPluralRes
import pl.paullettuce.dayscountdown.data.ToDoItem
import pl.paullettuce.dayscountdown.features.to_do_list.ToDoAdapter
import pl.paullettuce.dayscountdown.view.TimeUnitPluralizingListAdapter
import pl.paullettuce.dayscountdown.view.DateTimePicker
import pl.paullettuce.dayscountdown.view.MinMaxEditText
import javax.inject.Inject

@AndroidEntryPoint
class DeadlinePageFragment: Fragment(R.layout.fragment_deadline_page),
    DeadlinePageContract.View {

    @Inject lateinit var presenter: DeadlinePageContract.Presenter
    @Inject lateinit var listAdapter: TimeUnitPluralizingListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupReminderIntervalET()
        setupReminderTimeUnitsSpinner()
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
        listAdapter.quantity = intervalValue
    }

    override fun showReminderTimeUnits(units: List<TimeUnitToPluralRes>, selectItemIndex: Int) {
        listAdapter.apply {
            clear()
            addAll(units)
        }
        reminderIntervalTimeUnitSpinner.setSelection(selectItemIndex)
    }

    override fun showThingsToDo(list: List<ToDoItem>) {
        val todolist = resources.getStringArray(R.array.mock_things_to_do).map { ToDoItem(it) }
        (thingsToDoRV.adapter as ToDoAdapter).setItems(todolist)
    }

    override fun showTimeLeftString(timeLeftString: String) {
        timeLeftTV.text = timeLeftString
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

    private fun setupReminderTimeUnitsSpinner() {
        reminderIntervalTimeUnitSpinner.adapter = listAdapter
    }

    private fun setupReminderIntervalET() {
        reminderIntervalET.setValueRange(REMINDER_INTERVAL_MIN_VALUE, REMINDER_INTERVAL_MAX_VALUE)
        reminderIntervalET.onChangeCallback = ReminderIntervalValueChangeCallback()
    }

    inner class ReminderIntervalValueChangeCallback : MinMaxEditText.OnChange {
        override fun onValueChange(number: Int) {
            listAdapter.quantity = number
            saveReminderInterval()
        }
    }
}