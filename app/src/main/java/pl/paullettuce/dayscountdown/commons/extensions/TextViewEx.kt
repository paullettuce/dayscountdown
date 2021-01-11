package pl.paullettuce.dayscountdown.commons.extensions

import android.graphics.Paint
import android.widget.TextView

fun TextView.showStrikeThrough(show: Boolean) {
    paintFlags =
        if (show) Paint.ANTI_ALIAS_FLAG or Paint.STRIKE_THRU_TEXT_FLAG
        else Paint.ANTI_ALIAS_FLAG and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}