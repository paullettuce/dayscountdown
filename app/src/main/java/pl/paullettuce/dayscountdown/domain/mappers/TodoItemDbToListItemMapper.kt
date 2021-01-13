package pl.paullettuce.dayscountdown.domain.mappers

import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist.TODO_ITEM_VIEW_TYPE
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

class TodoItemDbToListItemMapper: Mapper<TodoItem, ViewTypedListItem> {
    override fun map(input: TodoItem): ViewTypedListItem {
        return ViewTypedListItem(TODO_ITEM_VIEW_TYPE, input)
    }
}

class TodoItemDbToListItemListMapper(
    private val itemMapper: TodoItemDbToListItemMapper
): ListMapper<TodoItem, ViewTypedListItem> {
    override fun map(input: List<TodoItem>): List<ViewTypedListItem> {
        return input.map { itemMapper.map(it) }
    }
}