package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

interface DeleteTodoItemUseCase {
    operator fun invoke(todoItem: TodoItem): Completable
}

class DeleteTodoItemUseCaseImpl(
    private val todoItemsRepository: TodoItemsRepository
): DeleteTodoItemUseCase {
    override fun invoke(todoItem: TodoItem) = todoItemsRepository.delete(todoItem)
}