package pl.paullettuce.dayscountdown.domain.usecase

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

interface GetTodoListItemsUseCase {
    operator fun invoke(): LiveData<List<ViewTypedListItem>>
}