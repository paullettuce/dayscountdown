package pl.paullettuce.dayscountdown.domain.repository

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

interface TodoItemsRepository {
    fun getTodoItems(): Single<List<ViewTypedListItem>>
    fun saveTodoItem(todoItemText: String): Completable
}