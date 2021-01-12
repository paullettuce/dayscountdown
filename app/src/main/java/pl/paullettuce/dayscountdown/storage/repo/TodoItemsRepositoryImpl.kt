package pl.paullettuce.dayscountdown.storage.repo

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.storage.dao.TodoItemsDao
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

class TodoItemsRepositoryImpl(
    private val todoItemsDao: TodoItemsDao
): TodoItemsRepository {
    override fun getTodoItems(): Single<List<ToDoItem>> {
        return todoItemsDao.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveTodoItem(todoItemText: String): Completable {
        return todoItemsDao.insert(ToDoItem(todoItemText))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}