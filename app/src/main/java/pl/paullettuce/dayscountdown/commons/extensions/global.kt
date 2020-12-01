package pl.paullettuce.dayscountdown.commons.extensions

import android.util.Log

fun Any.logd(message: String) {
    Log.d(this::class.java.name, message)
}