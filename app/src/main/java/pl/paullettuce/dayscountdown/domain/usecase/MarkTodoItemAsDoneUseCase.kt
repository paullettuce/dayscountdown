package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

inline fun markAsDone(
    todoItemsRepository: TodoItemsRepository,
    todoItem: TodoItem
): Completable {
    return todoItemsRepository.markAsDone(todoItem)
}

typealias MarkAsDoneUseCase = (TodoItem) -> Completable