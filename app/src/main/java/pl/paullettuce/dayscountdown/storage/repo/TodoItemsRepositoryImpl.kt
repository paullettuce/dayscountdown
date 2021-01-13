package pl.paullettuce.dayscountdown.storage.repo

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.paullettuce.dayscountdown.domain.mappers.TodoItemDbToListItemListMapper
import pl.paullettuce.dayscountdown.domain.mappers.TodoItemDbToListItemMapper
import pl.paullettuce.dayscountdown.domain.mappers.map
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.storage.dao.TodoItemsDao
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

class TodoItemsRepositoryImpl(
    private val todoItemsDao: TodoItemsDao,
    private val dbToListItemListMapper: TodoItemDbToListItemListMapper
): TodoItemsRepository {
    override fun getTodoItems(): LiveData<List<ViewTypedListItem>> {
        return todoItemsDao.getAll()
            .map { dbToListItemListMapper.map(it) }
    }

    override fun saveTodoItem(todoItemText: String): Completable {
        return todoItemsDao.insert(TodoItem(todoItemText))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}