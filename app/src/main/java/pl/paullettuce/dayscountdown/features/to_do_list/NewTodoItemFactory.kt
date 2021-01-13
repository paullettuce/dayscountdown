package pl.paullettuce.dayscountdown.features.to_do_list

import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist.NEW_ITEM_VIEW_TYPE
import javax.inject.Inject

class NewTodoItemFactory @Inject constructor(){
    fun newItem(): ViewTypedListItem {
        return ViewTypedListItem(NEW_ITEM_VIEW_TYPE, null)
    }
}