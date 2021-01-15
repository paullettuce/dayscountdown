package pl.paullettuce.dayscountdown.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.paullettuce.dayscountdown.storage.dao.DeadlineDao
import pl.paullettuce.dayscountdown.storage.dao.TodoItemsDao
import pl.paullettuce.dayscountdown.storage.entity.DeadlineData
import pl.paullettuce.dayscountdown.storage.entity.TimeUnitConverter
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

@Database(entities = [TodoItem::class, DeadlineData::class], version = 1)
@TypeConverters(TimeUnitConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun todoItemsDao(): TodoItemsDao
    abstract fun deadlineDao(): DeadlineDao
}