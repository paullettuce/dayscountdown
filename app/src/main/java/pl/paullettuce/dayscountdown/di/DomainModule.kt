package pl.paullettuce.dayscountdown.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import pl.paullettuce.dayscountdown.domain.mappers.TodoItemDbToListItemListMapper
import pl.paullettuce.dayscountdown.domain.mappers.TodoItemDbToListItemMapper
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.domain.usecase.*
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideTodoItemDbToListItemMapper() = TodoItemDbToListItemMapper()

    @Provides
    @Singleton
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

    @Provides
    fun provideDeleteTodoItemUseCase(
        todoItemsRepository: TodoItemsRepository
    ): DeleteTodoItemUseCase = DeleteTodoItemUseCaseImpl(todoItemsRepository)

    @Provides
    fun provideMarkTodoItemAsDoneUseCase(
        todoItemsRepository: TodoItemsRepository
    ): MarkTodoItemAsDoneUseCase = MarkTodoItemAsDoneUseCaseImpl(todoItemsRepository)

    @Provides
    fun provideMarkTodoItemAsNotDoneUseCase(
        todoItemsRepository: TodoItemsRepository
    ): MarkTodoItemAsNotDoneUseCase = MarkTodoItemAsNotDoneUseCaseImpl(todoItemsRepository)

}