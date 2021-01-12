package pl.paullettuce.dayscountdown.storage.repo

import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.storage.entity.ToDoItem

class TodoItemsRepositoryImpl(

): TodoItemsRepository {
    override fun getTodoItems(): Single<Result<List<ToDoItem>>> {
        TODO("getTodoItems")
    }

    override fun saveTodoItem(todoItemText: String): Single<Result<ToDoItem>> {
        TODO("saveTodoItem $todoItemText")
    }
}