package pl.paullettuce.dayscountdown.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.paullettuce.dayscountdown.App
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.domain.usecase.GetTodoItemsUseCase
import pl.paullettuce.dayscountdown.domain.usecase.GetTodoItemsUseCaseImpl
import pl.paullettuce.dayscountdown.domain.usecase.SaveTodoItemUseCase
import pl.paullettuce.dayscountdown.domain.usecase.SaveTodoItemUseCaseImpl
import pl.paullettuce.dayscountdown.storage.AppDatabase
import pl.paullettuce.dayscountdown.storage.dao.TodoItemsDao
import pl.paullettuce.dayscountdown.storage.repo.TodoItemsRepositoryImpl

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {
    @Provides
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun provideTodoItemsDao(
        db: AppDatabase
    ): TodoItemsDao = db.todoItemsDao()

    @Provides
    fun provideTodoItemsRepo(
        todoItemsDao: TodoItemsDao
    ): TodoItemsRepository {
        return TodoItemsRepositoryImpl(todoItemsDao)
    }

    @Provides
    fun provideGetTodoItemsUseCase(
        todoItemsRepository: TodoItemsRepository
    ): GetTodoItemsUseCase = GetTodoItemsUseCaseImpl(todoItemsRepository)

    @Provides
    fun provideSaveTodoItemUseCase(
        todoItemsRepository: TodoItemsRepository
    ): SaveTodoItemUseCase = SaveTodoItemUseCaseImpl(todoItemsRepository)
}