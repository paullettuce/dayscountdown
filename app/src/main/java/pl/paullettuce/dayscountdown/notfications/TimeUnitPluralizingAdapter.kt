package pl.paullettuce.dayscountdown.notfications

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pl.paullettuce.dayscountdown.data.TimeUnitToPluralRes

class TimeUnitPluralizingAdapter(
    context: Context,
    private val items: MutableList<TimeUnitToPluralRes> = mutableListOf()
) : ArrayAdapter<TimeUnitToPluralRes>(context, android.R.layout.simple_spinner_item, items) {
    var quantity = 0
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    override fun getCount() = items.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        pluralizeTimeUnitStringRes(view, position)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        pluralizeTimeUnitStringRes(view, position)
        return view
    }

    private fun pluralizeTimeUnitStringRes(view: View, position: Int) {
        val textView = view.findViewById<TextView>(android.R.id.text1)
        val item = getItem(position) ?: return

        val text = context.resources.getQuantityText(item.resourceId, quantity)
        textView.text = text
    }
}
