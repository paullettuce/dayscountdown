package pl.paullettuce.dayscountdown.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.paullettuce.dayscountdown.domain.mappers.TodoItemDbToListItemListMapper
import pl.paullettuce.dayscountdown.domain.mappers.TodoItemDbToListItemMapper
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.domain.usecase.GetTodoListItemsUseCase
import pl.paullettuce.dayscountdown.domain.usecase.GetTodoListItemsUseCaseImpl
import pl.paullettuce.dayscountdown.domain.usecase.SaveTodoItemUseCase
import pl.paullettuce.dayscountdown.domain.usecase.SaveTodoItemUseCaseImpl

@InstallIn(ApplicationComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideTodoItemDbToListItemMapper() = TodoItemDbToListItemMapper()

    @Provides
    fun provideTodoItemDbToListItemListMapper(
        itemMapper: TodoItemDbToListItemMapper
    ) = TodoItemDbToListItemListMapper(itemMapper)

    @Provides
    fun provideGetTodoItemsUseCase(
        todoItemsRepository: TodoItemsRepository
    ): GetTodoListItemsUseCase = GetTodoListItemsUseCaseImpl(todoItemsRepository)

    @Provides
    fun provideSaveTodoItemUseCase(
        todoItemsRepository: TodoItemsRepository
    ): SaveTodoItemUseCase = SaveTodoItemUseCaseImpl(todoItemsRepository)

}