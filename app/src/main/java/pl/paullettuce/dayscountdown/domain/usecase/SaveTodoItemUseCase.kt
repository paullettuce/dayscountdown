package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.storage.entity.ToDoItem

interface SaveTodoItemUseCase {
    operator fun invoke(todoItemText: String): Single<Result<ToDoItem>>
}