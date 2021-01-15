package pl.paullettuce.dayscountdown.storage.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.paullettuce.dayscountdown.domain.mappers.TodoItemDbToListItemListMapper
import pl.paullettuce.dayscountdown.domain.mappers.mapNotNull
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
            .mapNotNull {
                dbToListItemListMapper.map(it)
            }
    }

    override fun saveTodoItem(todoItemText: String): Completable {
        return todoItemsDao.insert(TodoItem(todoItemText))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun markAsDone(todoItem: TodoItem): Completable {
        return todoItemsDao.markAsDone(todoItem.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun markAsNotDone(todoItem: TodoItem): Completable {
        return todoItemsDao.markAsNotDone(todoItem.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun delete(todoItem: TodoItem): Completable {
        return todoItemsDao.delete(todoItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}