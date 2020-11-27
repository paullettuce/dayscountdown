package pl.paullettuce.dayscountdown.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.paullettuce.dayscountdown.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .addToBackStack("gg")
        supportFragmentManager.popBackStackImmediate("gg",0)
    }
}
