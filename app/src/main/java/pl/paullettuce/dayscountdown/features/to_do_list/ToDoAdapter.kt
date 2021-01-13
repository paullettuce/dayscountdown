package pl.paullettuce.dayscountdown.features.to_do_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.presentation.list_tools.ViewHolder
import pl.paullettuce.dayscountdown.presentation.list_tools.ViewHolderProvider
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

class ToDoAdapter(
    private val interaction: Interaction,
    private val viewHolderProvider: ViewHolderProvider,
    diffUtilCallback: DiffUtil.ItemCallback<ViewTypedListItem>
): ListAdapter<ViewTypedListItem, ViewHolder>(diffUtilCallback) {
    private var items = mutableListOf<ViewTypedListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return viewHolderProvider.createViewHolder(parent, viewType)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindViewHolder(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewHolderTypeId
    }

    /**
     * @return true if item was inserted, false if not
     */
    fun insertEmptyItem(): Boolean {
        if (!hasHeaderItem()) {
            items.add(0, EmptyTodoItem())
            notifyItemInserted(0)
            return true
        }
        return false
    }

//    fun setItems(items: List<TodoItem>) {
//        this.items.clear()
//        this.items.addAll(items)
//        notifyDataSetChanged()
//    }

    private fun delete(position: Int) {
        interaction.delete(items[position] as TodoItem)
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun toggleDone(indexOf: Int) {
        val item = items[indexOf] as TodoItem
        if (item.done) {
            interaction.markAsNotDone(item)
            item.done = false
        } else {
            interaction.markAsDone(item)
            item.done = true
        }
        notifyItemChanged(indexOf)
    }

    private fun deleteHeaderItem() {
        if (hasHeaderItem()) {
            items.removeAt(0)
            notifyItemRemoved(0)
        }
    }

    private fun hasHeaderItem() = items.isNotEmpty() && items[0] is EmptyTodoItem

    interface Interaction {
        fun saveTodoItem(text: String)
        fun markAsDone(item: TodoItem)
        fun markAsNotDone(item: TodoItem)
        fun delete(item: TodoItem)
    }
}

class EmptyTodoItem {

}