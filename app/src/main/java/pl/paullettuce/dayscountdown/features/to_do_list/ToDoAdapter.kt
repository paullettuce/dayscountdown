package pl.paullettuce.dayscountdown.features.to_do_list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_empty_edit_text.view.*
import kotlinx.android.synthetic.main.list_item_to_do.view.*
import pl.paullettuce.SwipeLayout
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.extensions.inflateLayout
import pl.paullettuce.dayscountdown.commons.extensions.showStrikeThrough
import pl.paullettuce.dayscountdown.data.ToDoItem

class ToDoAdapter(
    private val interaction: Interaction,
    private var items: MutableList<Any> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val EMPTY_ITEM = 0
    val DB_ITEM = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EMPTY_ITEM -> EmptyEditTextViewHolder(parent.inflateLayout(R.layout.list_item_empty_edit_text))
            DB_ITEM -> ToDoItemViewHolder(parent.inflateLayout(R.layout.list_item_to_do))
            else -> throw IllegalArgumentException("There is no view type $viewType")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EmptyEditTextViewHolder -> holder.bindView(interaction)
            is ToDoItemViewHolder -> holder.bindView(items[position] as ToDoItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = items[position]) {
            is EmptyToDoItem -> EMPTY_ITEM
            is ToDoItem -> DB_ITEM
            else -> throw IllegalArgumentException("There is no view type for ${item::class} class")
        }
    }

    fun insertEmptyItem() {
        items.add(0, EmptyToDoItem())
        notifyItemInserted(0)
    }

    fun setItems(items: List<Any>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun delete(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun toggleDone(indexOf: Int) {
        val item = items[indexOf] as ToDoItem
        item.done = !item.done
        notifyItemChanged(indexOf)
    }

    private fun replaceAtPositionWith(position: Int, toDoItem: ToDoItem) {
        items[position] = toDoItem
        notifyItemChanged(position)
    }

    private inner class ToDoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: ToDoItem) {
            itemView.todoTV.text = item.text
            itemView.swipeLayout.reset()
            itemView.swipeLayout.swipeListener = object : SwipeLayout.SwipeListener {
                override fun swipedToLeft() {
                    delete(adapterPosition)
                }

                override fun swipedToRight() {
                    toggleDone(adapterPosition)
                }
            }
            itemView.todoTV.showStrikeThrough(item.done)
        }
    }

    private inner class EmptyEditTextViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bindView(interaction: Interaction) {
            itemView.saveBtn.setOnClickListener {
                val todoItemText = itemView.todoEditText.text.toString()
                replaceAtPositionWith(adapterPosition, ToDoItem(todoItemText, true))
                interaction.createNewToDoItem(todoItemText)
            }
        }
    }

    interface Interaction {
        fun createNewToDoItem(text: String)
        fun markAsDone(item: ToDoItem)
        fun delete(item: ToDoItem)
    }
}

class EmptyToDoItem {

}