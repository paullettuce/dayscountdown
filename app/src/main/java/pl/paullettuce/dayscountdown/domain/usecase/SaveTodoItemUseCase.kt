package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository

interface SaveTodoItemUseCase {
    operator fun invoke(todoItemText: String): Completable
}

class SaveTodoItemUseCaseImpl(
    private val todoItemsRepository: TodoItemsRepository
): SaveTodoItemUseCase {
    override fun invoke(todoItemText: String) = todoItemsRepository.saveTodoItem(todoItemText)
}