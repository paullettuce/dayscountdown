package pl.paullettuce.dayscountdown.di

import android.content.Context
import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItemDiffCallback
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageContract
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePageFragment
import pl.paullettuce.dayscountdown.features.deadline_page.DeadlinePagePresenter
import pl.paullettuce.dayscountdown.features.to_do_list.ToDoAdapter
import pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist.NEW_ITEM_VIEW_TYPE
import pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist.NewItemViewHolderFactory
import pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist.TODO_ITEM_VIEW_TYPE
import pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist.TodoItemViewHolderFactory
import pl.paullettuce.dayscountdown.presentation.list_tools.ViewHolderProvider
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
    fun provideTodoItemsAdapter(
        fragment: DeadlinePageFragment,
        viewHolderProvider: ViewHolderProvider,
        diffCallback: ViewTypedListItemDiffCallback
    ): ToDoAdapter = ToDoAdapter(fragment, viewHolderProvider, diffCallback)

    @Provides
    fun providePluralizingAdapter(
        context: Context
    ): TimeUnitPluralizingListAdapter {
        return TimeUnitPluralizingListAdapter(context)
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

    @Provides
    fun provideTodoItemViewHolderFactory() = TodoItemViewHolderFactory()

    @Provides
    fun provideNewItemViewHolderFactory(
        fragment: DeadlinePageFragment
    ) = NewItemViewHolderFactory(fragment)

    @Provides
    fun provideViewHolderProvider(
        todoItemViewHolderFactory: TodoItemViewHolderFactory,
        newItemViewHolderFactory: NewItemViewHolderFactory
    ): ViewHolderProvider {
        val viewHolderProvider = ViewHolderProvider()
        viewHolderProvider.registerViewHolderFactory(TODO_ITEM_VIEW_TYPE, todoItemViewHolderFactory)
        viewHolderProvider.registerViewHolderFactory(NEW_ITEM_VIEW_TYPE, newItemViewHolderFactory)
        return viewHolderProvider
    }
}