package pl.paullettuce.dayscountdown.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.paullettuce.dayscountdown.storage.dao.TodoItemsDao
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

@Database(entities = [TodoItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoItemsDao(): TodoItemsDao
}