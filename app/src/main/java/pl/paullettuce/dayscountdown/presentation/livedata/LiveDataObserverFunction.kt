package pl.paullettuce.dayscountdown.presentation.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, f: (T?) -> Unit) {
    this.observe(owner, Observer<T> { t -> f(t) })
}

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, f: (T) -> Unit) {
    this.observe(owner, Observer<T> { t -> t?.let(f) })
}