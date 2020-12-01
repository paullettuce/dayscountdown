package pl.paullettuce.dayscountdown.commons.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    containerId: Int
) {
    supportFragmentManager.inTransaction {
        replace(containerId, fragment)
    }
}