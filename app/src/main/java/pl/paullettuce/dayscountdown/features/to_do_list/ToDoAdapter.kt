package pl.paullettuce.dayscountdown.features.to_do_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist.NEW_ITEM_VIEW_TYPE
import pl.paullettuce.dayscountdown.presentation.list_tools.ViewHolder
import pl.paullettuce.dayscountdown.presentation.list_tools.ViewHolderProvider
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

class ToDoAdapter(
    private val interaction: Interaction,
    private val viewHolderProvider: ViewHolderProvider,
    private val newItemFactory: NewTodoItemFactory,
    diffUtilCallback: DiffUtil.ItemCallback<ViewTypedListItem>
) : ListAdapter<ViewTypedListItem, ViewHolder>(diffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return viewHolderProvider.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindViewHolder(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewTypeId
    }

    /**
     * @return true if item was inserted, false if not
     */
    fun insertEmptyItem(): Boolean {
        if (!hasHeaderItem()) {
            val items = currentList.toMutableList()
            items.add(0, newItemFactory.newItem())
            submitList(items)
            return true
        }
        return false
    }

    fun deleteEmptyItem() {
        if (hasHeaderItem()) {
            val items = currentList.toMutableList()
            items.removeAt(0)
            submitList(items)
        }
    }

    private fun hasHeaderItem() =
        currentList.isNotEmpty() && currentList[0].viewTypeId == NEW_ITEM_VIEW_TYPE

    private fun delete(position: Int) {
//        interaction.delete(items[position] as TodoItem)
//        items.removeAt(position)
//        notifyItemRemoved(position)
    }

    private fun toggleDone(indexOf: Int) {
//        val item = items[indexOf] as TodoItem
//        if (item.done) {
//            interaction.markAsNotDone(item)
//            item.done = false
//        } else {
//            interaction.markAsDone(item)
//            item.done = true
//        }
//        notifyItemChanged(indexOf)
    }

    private fun deleteHeaderItem() {
//        if (hasHeaderItem()) {
//            items.removeAt(0)
//            notifyItemRemoved(0)
//        }
    }

    interface Interaction {
        fun saveTodoAndDeleteEditableItem(text: String)
        fun markAsDone(item: TodoItem)
        fun markAsNotDone(item: TodoItem)
        fun delete(item: TodoItem)
    }
}