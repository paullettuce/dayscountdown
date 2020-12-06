package pl.paullettuce.dayscountdown.notfications.builder

import android.content.Context
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.data.DeadlineData
import pl.paullettuce.dayscountdown.data.TimeLeft
import pl.paullettuce.dayscountdown.view.adapters.TimeLeftStringBuilder

abstract class NotificationContentFactory {
    abstract fun buildContent(): NotificationContent
}

class ReminderContentFactory(
    private val context: Context,
    private val deadlineData: DeadlineData
) : NotificationContentFactory() {
    private val timeLeftAdapter =
        TimeLeftStringBuilder(context, displayedUnitsLimit = 2)
    private val timeLeft = TimeLeft.betweenNowAndTimestamp(deadlineData.datetimeTimestamp)

    override fun buildContent(): NotificationContent {
        return if (hasBigTextContent()) {
            BigTextContent(bigTextContent())
        } else {
            OneLineContent(oneLineContent())
        }
    }

    private fun hasBigTextContent() = deadlineData.thingsToDo.isNotEmpty()

    private fun oneLineContent(): String {
        return getTimeLeftFormatted()
    }

    private fun bigTextContent(): String {
        val sb = StringBuilder()
        sb.appendln(getTimeLeftFormatted())
        sb.append(context.getString(R.string.things_to_do)).append(":")
        deadlineData.thingsToDo.forEachIndexed { index, item ->
            sb.appendln()
            sb.append(item.text)
        }
        return sb.toString()
    }

    private fun getTimeLeftFormatted(): String {
        return context.getString(R.string.time_left, timeLeftAdapter.toPluralString(timeLeft))
    }
}