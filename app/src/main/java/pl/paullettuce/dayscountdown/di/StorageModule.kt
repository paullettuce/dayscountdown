package pl.paullettuce.dayscountdown.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import pl.paullettuce.dayscountdown.domain.mappers.TodoItemDbToListItemListMapper
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.storage.AppDatabase
import pl.paullettuce.dayscountdown.storage.dao.TodoItemsDao
import pl.paullettuce.dayscountdown.storage.repo.TodoItemsRepositoryImpl
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object StorageModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoItemsDao(
        db: AppDatabase
    ): TodoItemsDao = db.todoItemsDao()

    @Provides
    @Singleton
    fun provideTodoItemsRepo(
        todoItemsDao: TodoItemsDao,
        itemsMapper: TodoItemDbToListItemListMapper
    ): TodoItemsRepository {
        return TodoItemsRepositoryImpl(todoItemsDao, itemsMapper)
    }
}