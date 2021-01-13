package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.storage.entity.TodoItem


interface MarkTodoItemAsDoneUseCase {
    operator fun invoke(todoItem: TodoItem): Completable
}

class MarkTodoItemAsDoneUseCaseImpl(
    private val todoItemsRepository: TodoItemsRepository
): MarkTodoItemAsDoneUseCase {
    override fun invoke(todoItem: TodoItem) = todoItemsRepository.markAsDone(todoItem)
}