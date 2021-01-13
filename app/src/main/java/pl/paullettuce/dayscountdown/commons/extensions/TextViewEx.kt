package pl.paullettuce.dayscountdown.commons.extensions

import android.content.res.ColorStateList
import android.graphics.Paint
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import pl.paullettuce.dayscountdown.R

fun TextView.showStrikeThrough(show: Boolean) {
    paintFlags =
        if (show) Paint.ANTI_ALIAS_FLAG or Paint.STRIKE_THRU_TEXT_FLAG
        else Paint.ANTI_ALIAS_FLAG and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

fun TextView.makeLSlightlyTransparent(shouldBeOpaque: Boolean) {
    alpha = if (shouldBeOpaque) 0.6f else 1f
}

fun EditText.showError(show: Boolean) {
    val colorStateList =
        if (show) {
            ColorStateList.valueOf(
                ContextCompat.getColor(
                    context,
                    R.color.editTextErrorTint
                )
            )
        } else {
            null
        }
    background.setTintList(colorStateList)
    setHintTextColor(colorStateList)
}