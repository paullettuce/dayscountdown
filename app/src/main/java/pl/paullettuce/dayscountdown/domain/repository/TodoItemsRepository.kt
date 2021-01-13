package pl.paullettuce.dayscountdown.domain.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

interface TodoItemsRepository {
    fun getTodoItems(): LiveData<List<ViewTypedListItem>>
    fun saveTodoItem(todoItemText: String): Completable
}