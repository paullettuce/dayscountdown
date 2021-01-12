package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

interface SaveTodoItemUseCase {
    operator fun invoke(todoItemText: String): Completable
}