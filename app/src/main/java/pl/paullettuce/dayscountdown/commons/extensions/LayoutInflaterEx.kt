package pl.paullettuce.dayscountdown.commons.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflateLayout(
    @LayoutRes layoutRes: Int
) = LayoutInflater.from(this.context).inflate(layoutRes, this, false)