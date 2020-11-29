package pl.paullettuce.dayscountdown.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageContract
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageFragment
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePagePresenter

@Module
@InstallIn(FragmentComponent::class)
abstract class DeadlinePageModule {

    @Binds
    @FragmentScoped
    abstract fun bindDeadlinePageView(
        deadlinePageFragment: DeadlinePageFragment
    ): DeadlinePageContract.View

    @Binds
    @FragmentScoped
    abstract fun bindDeadlinePagePresenter(
        presenter: DeadlinePagePresenter
    ): DeadlinePageContract.Presenter
}