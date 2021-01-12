package pl.paullettuce.dayscountdown.domain.repository

import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.storage.entity.ToDoItem

interface TodoItemsRepository {
    fun getTodoItems(): Single<Result<List<ToDoItem>>>
    fun saveTodoItem(todoItemText: String): Single<Result<ToDoItem>>
}