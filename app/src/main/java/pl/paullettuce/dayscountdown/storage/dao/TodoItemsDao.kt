package pl.paullettuce.dayscountdown.storage.dao

import androidx.room.*
import pl.paullettuce.dayscountdown.storage.entity.ToDoItem

@Dao
interface TodoItemsDao {

    @Query("SELECT * FROM todoitem")
    fun getAll(): List<ToDoItem>

    @Insert
    fun insert(toDoItem: ToDoItem)

    @Delete
    fun delete(toDoItem: ToDoItem)

    @Query("UPDATE todoitem SET done =:done WHERE id=:toDoItemId")
    fun markAsDone(toDoItemId: Int, done: Boolean)

}