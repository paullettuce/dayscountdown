package pl.paullettuce.dayscountdown.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

@Dao
interface TodoItemsDao {

    @Query("SELECT * FROM todoitem ORDER BY id DESC")
    fun getAll(): LiveData<List<TodoItem>>

    @Insert
    fun insert(todoItem: TodoItem): Completable

    @Delete
    fun delete(todoItem: TodoItem): Completable

    @Query("UPDATE todoitem SET done = 1 WHERE id=:todoItemId")
    fun markAsDone(todoItemId: Long): Completable

    @Query("UPDATE todoitem SET done = 0 WHERE id=:todoItemId")
    fun markAsNotDone(todoItemId: Long): Completable
}