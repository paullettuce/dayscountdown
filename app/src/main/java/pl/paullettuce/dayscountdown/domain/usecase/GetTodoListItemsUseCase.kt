package pl.paullettuce.dayscountdown.domain.usecase

import androidx.lifecycle.LiveData
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.domain.repository.TodoItemsRepository

interface GetTodoListItemsUseCase {
    operator fun invoke(): LiveData<List<ViewTypedListItem>>
}

class GetTodoListItemsUseCaseImpl(
    private val todoItemsRepository: TodoItemsRepository
): GetTodoListItemsUseCase {
    override fun invoke() = todoItemsRepository.getTodoItems()
}