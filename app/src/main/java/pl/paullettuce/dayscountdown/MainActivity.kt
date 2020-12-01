package pl.paullettuce.dayscountdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.extensions.replaceFragment
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageFragment
import javax.inject.Inject  

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
