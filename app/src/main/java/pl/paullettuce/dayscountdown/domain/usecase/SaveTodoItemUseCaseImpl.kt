package pl.paullettuce.dayscountdown.domain.usecase

import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository

class SaveTodoItemUseCaseImpl(
    private val todoItemsRepository: TodoItemsRepository
): SaveTodoItemUseCase {
    override fun invoke(todoItemText: String) = todoItemsRepository.saveTodoItem(todoItemText)
}