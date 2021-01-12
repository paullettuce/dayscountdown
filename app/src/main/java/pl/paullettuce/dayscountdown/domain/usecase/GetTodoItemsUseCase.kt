package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

interface GetTodoItemsUseCase {
    operator fun invoke(): Single<List<TodoItem>>
}