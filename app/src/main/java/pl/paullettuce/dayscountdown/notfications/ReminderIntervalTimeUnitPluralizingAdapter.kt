package pl.paullettuce.dayscountdown.notfications

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.PluralsRes
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.logd
import pl.paullettuce.dayscountdown.view.deadline_page.DeadlinePageFragment
import java.util.concurrent.TimeUnit

class ReminderIntervalTimeUnitPluralizingAdapter(
    context: Context,
    val items: List<TimeUnitToPluralResource>
) : ArrayAdapter<TimeUnitToPluralResource>(context, android.R.layout.simple_spinner_item, items) {
    var quantity = 0
        set(value) {
            field = value
            items.forEach { it.quantity = value }
            notifyDataSetChanged()
        }

    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

    }

    override fun getCount() = items.size
}

class TimeUnitToPluralResource(
    private val context: Context,
    val timeUnit: TimeUnit,
    @PluralsRes val resourceId: Int,
    var quantity: Int
) {
    override fun toString(): String {
        return context.resources.getQuantityString(resourceId, quantity)
    }
}