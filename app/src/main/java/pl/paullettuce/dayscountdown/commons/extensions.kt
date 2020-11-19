package pl.paullettuce.dayscountdown.commons

import android.util.Log

fun Any.logd(message: String) {
    Log.d(this::class.java.name, message)
}