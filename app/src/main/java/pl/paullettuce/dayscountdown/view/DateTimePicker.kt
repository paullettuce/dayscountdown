package pl.paullettuce.dayscountdown.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.util.*
import java.util.Calendar.*

class DateTimePicker(
    private val context: Context,
    private val pickingFinishedCallback: (datetimeMillis: Long) -> Unit
) {
    private val pickedDate = Calendar.getInstance()

    fun pickDateAndTime(
        initialDatetimeMillis: Long
    ) {
        pickTime(initialDatetimeMillis)
//        pickDate(initialDatetimeMillis, false)
    }

    fun pickTime(
        initialDatetimeMillis: Long
    ) {
        val calendar = initiateCalendarWithMillisSinceEpoch(initialDatetimeMillis)
        pickTime(calendar.get(HOUR_OF_DAY), calendar.get(MINUTE))
    }

    private fun pickDate(
        initialDatetimeMillis: Long,
        pickTimeToo: Boolean
    ) {
        val calendar = initiateCalendarWithMillisSinceEpoch(initialDatetimeMillis)

        showDatePickerWithInitialDate(
            calendar.get(YEAR),
            calendar.get(MONTH),
            calendar.get(DAY_OF_MONTH)
        ) { year, month, day ->
            pickedDate.set(year, month, day)

            if (pickTimeToo) {
                pickTime(initialDatetimeMillis)
            } else {
                pickingFinished()
            }
        }
    }

    private fun pickTime(
        initialHour: Int,
        initialMinute: Int
    ) {
        showTimePicker(
            initialHour, initialMinute
        ) { hour, minute ->
            pickedDate.set(HOUR_OF_DAY, hour)
            pickedDate.set(MINUTE, minute)

            pickingFinished()
        }
    }

    private fun initiateCalendarWithMillisSinceEpoch(initialDatetimeMillis: Long): Calendar {
        val calendar = getInstance()
        calendar.timeInMillis = initialDatetimeMillis
        return calendar
    }

    private fun showDatePickerWithInitialDate(
        initialYear: Int,
        initialMonth: Int,
        initialDay: Int,
        datePickedCallback: (year: Int, month: Int, day: Int) -> Unit
    ) {
        DatePickerDialog(
            context!!,
            { view, year, month, dayOfMonth ->
                datePickedCallback(year, month, dayOfMonth)
            },
            initialYear, initialMonth, initialDay
        ).show()
    }

    private fun showTimePicker(
        initialHour: Int,
        initialMinute: Int,
        timePickedCallback: (hour: Int, minute: Int) -> Unit
    ) {
        TimePickerDialog(
            context,
            { view, hourOfDay, minute ->
                timePickedCallback(hourOfDay, minute)
            },
            initialHour, initialMinute,
            true
        ).show()
    }

    private fun pickingFinished() {
        pickingFinishedCallback(pickedDate.timeInMillis)
    }
}
