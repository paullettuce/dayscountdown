package pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import kotlinx.android.synthetic.main.list_item_empty_edit_text.view.*
import kotlinx.android.synthetic.main.list_item_to_do.view.*
import pl.paullettuce.SwipeLayout
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.extensions.hideKeyboard
import pl.paullettuce.dayscountdown.commons.extensions.makeLSlightlyTransparent
import pl.paullettuce.dayscountdown.commons.extensions.showError
import pl.paullettuce.dayscountdown.commons.extensions.showStrikeThrough
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.features.to_do_list.ToDoAdapter
import pl.paullettuce.dayscountdown.presentation.list_tools.ViewHolder
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

class TodoItemViewHolder(
    itemView: View,
    private val interaction: ToDoAdapter.Interaction
) : ViewHolder(itemView) {

    override fun onBindViewHolder(viewTypedListItem: ViewTypedListItem) {
        val item = viewTypedListItem.content as TodoItem
        itemView.todoTV.text = item.text
        itemView.swipeLayout.reset()
        itemView.swipeLayout.swipeListener = object : SwipeLayout.SwipeListener {
            override fun swipedToLeft() = interaction.delete(item)
            override fun swipedToRight() {
                if (item.done) {
                    interaction.markAsNotDone(item)
                } else {
                    interaction.markAsNotDone(item)
                }
            }
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

class NewTodoItemViewHolder(
    itemView: View,
    private val interaction: ToDoAdapter.Interaction
) : ViewHolder(itemView) {

    override fun onBindViewHolder(viewTypedListItem: ViewTypedListItem) {
        itemView.saveBtn.setOnClickListener { v ->
            saveTodoItem(v)
        }
        itemView.todoEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                saveTodoItem(v)
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        itemView.todoEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) itemView.todoEditText.showError(false)
        }
        itemView.newItemSwipeLayout.reset()
        itemView.newItemSwipeLayout.swipeListener = object : SwipeLayout.SwipeListener {
            override fun swipedToLeft() = interaction.deleteEditableItem()

            override fun swipedToRight() { }
        }
    }

    private fun saveTodoItem(view: View) {
        val todoItemText = itemView.todoEditText.text.toString()
        if (todoItemText.isBlank()) {
            itemView.todoEditText.showError(true)
        } else {
            view.hideKeyboard()
            itemView.todoEditText.text?.clear()
            interaction.deleteEditableItem()
            interaction.saveTodo(todoItemText)
        }
    }
}