package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.storage.entity.ToDoItem

interface GetTodoItemsUseCase {
    operator fun invoke(): Single<Result<List<ToDoItem>>>
}