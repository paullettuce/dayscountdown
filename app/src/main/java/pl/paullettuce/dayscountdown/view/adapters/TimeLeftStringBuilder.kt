package pl.paullettuce.dayscountdown.view.adapters

import android.content.Context
import androidx.annotation.PluralsRes
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.data.TimeLeft

class TimeLeftStringBuilder(
    private val context: Context,
    private val displayedUnitsLimit: Int = Int.MAX_VALUE,
    private val unitsSeparator: Separator = Separator.Space()
) {
    private var displayedUnitsCount = 0

    fun toPluralString(timeLeft: TimeLeft): String {
        displayedUnitsCount = 0
        val sb = StringBuilder()
        tryAppendUnit(sb, R.plurals.days, timeLeft.days)
        tryAppendUnit(sb, R.plurals.hours, timeLeft.hours)
        tryAppendUnit(sb, R.plurals.minutes, timeLeft.minutes)
        return sb.toString()
    }

    private fun tryAppendUnit(sb: StringBuilder, @PluralsRes pluralRes: Int, quantity: Long) {
        if (quantity <= 0 || displayedUnitsCount >= displayedUnitsLimit) return
        if (displayedUnitsCount > 0) sb.appendSeparator()
        sb
            .append(quantity.toString()).append(' ')
            .append(getPluralString(pluralRes, quantity.toInt()))
        displayedUnitsCount++
    }

    private fun getPluralString(@PluralsRes pluralRes: Int, quantity: Int) =
        context.resources.getQuantityText(pluralRes, quantity)

    private fun StringBuilder.appendSeparator() = this.append(unitsSeparator.char)
}

sealed class Separator(val char: Char) {
    class Space : Separator(' ')
    class NewLine : Separator('\n')
    class Custom(separator: Char) : Separator(separator)
}