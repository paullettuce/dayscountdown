package pl.paullettuce.dayscountdown.features.to_do_list

import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_empty_edit_text.view.*
import kotlinx.android.synthetic.main.list_item_to_do.view.*
import pl.paullettuce.SwipeLayout
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.extensions.hideKeyboard
import pl.paullettuce.dayscountdown.commons.extensions.inflateLayout
import pl.paullettuce.dayscountdown.commons.extensions.makeLSlightlyTransparent
import pl.paullettuce.dayscountdown.commons.extensions.showStrikeThrough
import pl.paullettuce.dayscountdown.data.ToDoItem

class ToDoAdapter(
    private val interaction: Interaction,
    private var items: MutableList<Any> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val EMPTY_ITEM_TYPE = 0
    private val DB_ITEM_Type = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EMPTY_ITEM_TYPE ->
                EmptyEditTextViewHolder(
                    parent.inflateLayout(R.layout.list_item_empty_edit_text),
                    interaction
                )
            DB_ITEM_Type ->
                ToDoItemViewHolder(
                    parent.inflateLayout(R.layout.list_item_to_do))
            else -> throw IllegalArgumentException("There is no view type $viewType")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EmptyEditTextViewHolder -> holder.bindView()
            is ToDoItemViewHolder -> holder.bindView(items[position] as ToDoItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = items[position]) {
            is EmptyToDoItem -> EMPTY_ITEM_TYPE
            is ToDoItem -> DB_ITEM_Type
            else -> throw IllegalArgumentException("There is no view type for ${item::class} class")
        }
    }

    /**
     * @return true if item was inserted, false if not
     */
    fun insertEmptyItem(): Boolean {
        if (!hasHeaderItem()) {
            items.add(0, EmptyToDoItem())
            notifyItemInserted(0)
            return true
        }
        return false
    }

    fun setItems(items: List<ToDoItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private fun delete(position: Int) {
        interaction.delete(items[position] as ToDoItem)
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun toggleDone(indexOf: Int) {
        val item = items[indexOf] as ToDoItem
        if (item.done) {
            interaction.markAsNotDone(item)
            item.done = false
        } else {
            interaction.markAsDone(item)
            item.done = true
        }
        notifyItemChanged(indexOf)
    }

    private fun hasHeaderItem() = items[0] is EmptyToDoItem

    private fun deleteHeaderItem() {
        if (hasHeaderItem()) {
            items.removeAt(0)
            notifyItemRemoved(0)
        }
    }

    private inner class ToDoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: ToDoItem) {
            itemView.todoTV.text = item.text
            itemView.swipeLayout.reset()
            itemView.swipeLayout.swipeListener = object : SwipeLayout.SwipeListener {
                override fun swipedToLeft() = delete(adapterPosition)
                override fun swipedToRight() = toggleDone(adapterPosition)
            }
            itemView.todoTV.showStrikeThrough(item.done)
            itemView.todoTV.makeLSlightlyTransparent(item.done)
            itemView.doneIV.setDoneNotDoneIcon(item.done)
        }

        private fun ImageView.setDoneNotDoneIcon(done: Boolean) {
            if (done) setImageResource(R.drawable.ic_not_done)
            else setImageResource(R.drawable.ic_done)
        }
    }

    private inner class EmptyEditTextViewHolder(
        itemView: View,
        private val interaction: Interaction
    ) :
        RecyclerView.ViewHolder(itemView) {
        fun bindView() {
            itemView.saveBtn.setOnClickListener {
                createNewTodoItem()
            }
            itemView.todoEditText.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    createNewTodoItem()
                    v.hideKeyboard()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
            itemView.newItemSwipeLayout.reset()
            itemView.newItemSwipeLayout.swipeListener = object : SwipeLayout.SwipeListener {
                override fun swipedToLeft() = deleteHeaderItem()

                override fun swipedToRight() { }
            }
        }

        private fun createNewTodoItem() {
            val todoItemText = itemView.todoEditText.text.toString()
            interaction.createNewToDoItem(todoItemText)
        }

    }

    interface Interaction {
        fun createNewToDoItem(text: String)
        fun markAsDone(item: ToDoItem)
        fun markAsNotDone(item: ToDoItem)
        fun delete(item: ToDoItem)
    }
}

class EmptyToDoItem {

}