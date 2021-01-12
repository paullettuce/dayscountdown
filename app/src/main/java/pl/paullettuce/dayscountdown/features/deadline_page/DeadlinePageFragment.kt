package pl.paullettuce.dayscountdown.features.deadline_page

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_deadline_page.*
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.REMINDER_INTERVAL_MAX_VALUE
import pl.paullettuce.dayscountdown.REMINDER_INTERVAL_MIN_VALUE
import pl.paullettuce.dayscountdown.commons.RecyclerViewMargin
import pl.paullettuce.dayscountdown.data.TimeUnitToPluralRes
import pl.paullettuce.dayscountdown.storage.entity.TodoItem
import pl.paullettuce.dayscountdown.features.to_do_list.ToDoAdapter
import pl.paullettuce.dayscountdown.view.TimeUnitPluralizingListAdapter
import pl.paullettuce.dayscountdown.view.DateTimePicker
import pl.paullettuce.dayscountdown.view.MinMaxEditText
import javax.inject.Inject

@AndroidEntryPoint
class DeadlinePageFragment : Fragment(R.layout.fragment_deadline_page),
    DeadlinePageContract.View, ToDoAdapter.Interaction {

    @Inject
    lateinit var presenter: DeadlinePageContract.Presenter

    @Inject
    lateinit var timeUnitsSpinnerAdapter: TimeUnitPluralizingListAdapter

    @Inject
    lateinit var thingsToDoAdapter: ToDoAdapter

    override fun showMsg(msg: String) {
        activity?.runOnUiThread {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        }
    }

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
        timeUnitsSpinnerAdapter.quantity = intervalValue
    }

    override fun showReminderTimeUnits(units: List<TimeUnitToPluralRes>, selectItemIndex: Int) {
        timeUnitsSpinnerAdapter.apply {
            clear()
            addAll(units)
        }
        reminderIntervalTimeUnitSpinner.setSelection(selectItemIndex)
    }

    override fun showThingsToDo(items: List<TodoItem>) {
        (thingsToDoRV.adapter as ToDoAdapter).setItems(items)
    }

    override fun saveTodoItem(text: String) {
        presenter.saveTodoItem(text)
    }

    override fun markAsDone(item: TodoItem) {
        presenter.markAsDone(item)
    }

    override fun markAsNotDone(item: TodoItem) {
        presenter.markAsNotDone(item)
    }

    override fun delete(item: TodoItem) {
        presenter.deleteTodoItem(item)
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
        thingsToDoRV.adapter = thingsToDoAdapter
        thingsToDoRV.addItemDecoration(
            RecyclerViewMargin(verticalMarginDp = R.dimen.recycler_view_item_margin)
        )
        thingsToDoAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                scrollToTodoListStart()
            }
        })
    }

    private fun setListeners() {
        dateTimePickBtn.setOnClickListener {
            presenter.openDeadlineDatetimePicker()
        }
        reminderCheckbox.setOnCheckedChangeListener { _, isChecked ->
            presenter.toggleNotifications(isChecked, 1L)
        }
        addTodoItemBtn.setOnClickListener {
            val alreadyHadEmptyItem = !thingsToDoAdapter.insertEmptyItem()
            if (alreadyHadEmptyItem) scrollToTodoListStart()
        }
    }

    private fun scrollToTodoListStart() {
        thingsToDoRV.scrollToPosition(0)
    }

    private fun setupReminderTimeUnitsSpinner() {
        reminderIntervalTimeUnitSpinner.adapter = timeUnitsSpinnerAdapter
    }

    private fun setupReminderIntervalET() {
        reminderIntervalET.setValueRange(REMINDER_INTERVAL_MIN_VALUE, REMINDER_INTERVAL_MAX_VALUE)
        reminderIntervalET.onChangeCallback = ReminderIntervalValueChangeCallback()
    }

    inner class ReminderIntervalValueChangeCallback : MinMaxEditText.OnChange {
        override fun onValueChange(number: Int) {
            timeUnitsSpinnerAdapter.quantity = number
            saveReminderInterval()
        }
    }
}