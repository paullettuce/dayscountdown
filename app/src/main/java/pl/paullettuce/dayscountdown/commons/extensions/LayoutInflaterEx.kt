package pl.paullettuce.dayscountdown.commons.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateLayout(
    @LayoutRes layoutRes: Int,
    layoutParams: ViewGroup.LayoutParams
): View {
    val view = LayoutInflater.from(this.context).inflate(layoutRes, this, false)
    view.layoutParams = layoutParams
    return view
}