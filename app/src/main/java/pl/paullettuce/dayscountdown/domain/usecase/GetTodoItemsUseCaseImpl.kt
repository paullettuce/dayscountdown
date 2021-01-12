package pl.paullettuce.dayscountdown.domain.usecase

import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository

class GetTodoItemsUseCaseImpl(
    private val todoItemsRepository: TodoItemsRepository
): GetTodoItemsUseCase {
    override fun invoke() = todoItemsRepository.getTodoItems()
}