package pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist

import android.view.ViewGroup
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.extensions.inflateLayout
import pl.paullettuce.dayscountdown.features.to_do_list.ToDoAdapter
import pl.paullettuce.dayscountdown.presentation.list_tools.ViewHolderFactory

class TodoItemViewHolderFactory: ViewHolderFactory {
    override fun createInstance(parent: ViewGroup) = TodoItemViewHolder(
        parent.inflateLayout(R.layout.list_item_to_do)
    )
}

class NewItemViewHolderFactory(private val interaction: ToDoAdapter.Interaction): ViewHolderFactory {
    override fun createInstance(parent: ViewGroup) = NewTodoItemViewHolder(
        parent.inflateLayout(R.layout.list_item_empty_edit_text),
        interaction
    )
}
