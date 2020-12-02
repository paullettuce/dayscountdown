package pl.paullettuce.dayscountdown.notfications.reminder

import android.content.Context
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.data.DeadlineData

class ReminderContentBuilder(
    private val context: Context,
    private val deadlineData: DeadlineData
) {
    fun hasBigTextContent() = deadlineData.thingsToDo.isNotEmpty()

    fun buildSubtitleContent(): String {
        return getTimeLeftFormatted()
    }

    fun buildBigTextContent(): String {
        val sb = StringBuilder()
        sb.appendln(getTimeLeftFormatted())
        sb.append(context.getString(R.string.things_to_do)).append(":").appendln()
        deadlineData.thingsToDo.forEachIndexed { index, item ->
            sb.append(item.text)
            if (index != deadlineData.thingsToDo.lastIndex) sb.appendln()
        }
        return sb.toString()
    }

    private fun getTimeLeftFormatted() = "TIme left is 12348132n049 hours and 123 hou"
}