package pl.paullettuce.dayscountdown.di

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.domain.usecase.GetTodoItemsUseCase
import pl.paullettuce.dayscountdown.domain.usecase.GetTodoItemsUseCaseImpl
import pl.paullettuce.dayscountdown.domain.usecase.SaveTodoItemUseCase
import pl.paullettuce.dayscountdown.domain.usecase.SaveTodoItemUseCaseImpl
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageContract
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageFragment
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePagePresenter
import pl.paullettuce.dayscountdown.storage.AppDatabase
import pl.paullettuce.dayscountdown.storage.dao.TodoItemsDao
import pl.paullettuce.dayscountdown.storage.repo.TodoItemsRepositoryImpl
import pl.paullettuce.dayscountdown.view.TimeUnitPluralizingListAdapter
import pl.paullettuce.dayscountdown.view.adapters.Separator
import pl.paullettuce.dayscountdown.view.adapters.TimeLeftStringBuilder

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
    ): TimeUnitPluralizingListAdapter {
        return TimeUnitPluralizingListAdapter(
            context
        )
    }

    @Provides
    fun provideTimeLeftToPluralizedStringAdapter(
        context: Context
    ): TimeLeftStringBuilder {
        return TimeLeftStringBuilder(
            context,
            displayedUnitsLimit = 3,
            unitsSeparator = Separator.NewLine()
        )
    }
}