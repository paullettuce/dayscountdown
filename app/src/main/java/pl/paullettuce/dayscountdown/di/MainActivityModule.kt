package pl.paullettuce.dayscountdown.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageFragment

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @Provides
    @ActivityScoped
    fun provideAppCompatActivity(
        activity: Activity
    ): AppCompatActivity {
        return activity as AppCompatActivity
    }

    @Provides
    @ActivityScoped
    fun provideDeadlinePageFragment(

    ): DeadlinePageFragment {
        return DeadlinePageFragment()
    }
}