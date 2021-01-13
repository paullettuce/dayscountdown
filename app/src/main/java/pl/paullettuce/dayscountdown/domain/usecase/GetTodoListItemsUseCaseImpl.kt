package pl.paullettuce.dayscountdown.domain.usecase

import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository

class GetTodoListItemsUseCaseImpl(
    private val todoItemsRepository: TodoItemsRepository
): GetTodoListItemsUseCase {
    override fun invoke() = todoItemsRepository.getTodoItems()
}