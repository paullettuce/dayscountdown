package pl.paullettuce.dayscountdown.di

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageContract
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageFragment
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePagePresenter
import pl.paullettuce.dayscountdown.notfications.TimeUnitPluralizingAdapter

@Module
@InstallIn(FragmentComponent::class)
abstract class DeadlinePageModule {
    @Binds
    abstract fun provideDeadlinePageView(
        fragment: DeadlinePageFragment
    ): DeadlinePageContract.View

    @Binds
    abstract fun provideDeadlinePagePresenter(
        presenter: DeadlinePagePresenter
    ): DeadlinePageContract.Presenter
}

@InstallIn(FragmentComponent::class)
@Module
object DeadlinePageFragmentModule {
    @Provides
    fun provideDeadlinePageFragment(
        fragment: Fragment
    ): DeadlinePageFragment {
        return fragment as DeadlinePageFragment
    }

    @Provides
    fun provideContext(
        fragment: Fragment
    ): Context {
        return fragment.requireContext()
    }

    @Provides
    fun providePluralizingAdapter(
        context: Context
    ): TimeUnitPluralizingAdapter {
        return TimeUnitPluralizingAdapter(context)
    }
}