package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

interface MarkTodoItemAsNotDoneUseCase {
    operator fun invoke(todoItem: TodoItem): Completable
}

class MarkTodoItemAsNotDoneUseCaseImpl(
    private val todoItemsRepository: TodoItemsRepository
): MarkTodoItemAsNotDoneUseCase {
    override fun invoke(todoItem: TodoItem) = todoItemsRepository.markAsNotDone(todoItem)
}