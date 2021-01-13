package pl.paullettuce.dayscountdown.presentation.deadlinepage.todolist

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import kotlinx.android.synthetic.main.list_item_empty_edit_text.view.*
import kotlinx.android.synthetic.main.list_item_to_do.view.*
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.commons.extensions.hideKeyboard
import pl.paullettuce.dayscountdown.commons.extensions.makeLSlightlyTransparent
import pl.paullettuce.dayscountdown.commons.extensions.showStrikeThrough
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import pl.paullettuce.dayscountdown.features.to_do_list.ToDoAdapter
import pl.paullettuce.dayscountdown.presentation.list_tools.ViewHolder
import pl.paullettuce.dayscountdown.storage.entity.TodoItem

//abstract class TodoAdapterViewHolder(itemView: View) : ViewHolder(itemView)

class TodoItemViewHolder(itemView: View) : ViewHolder(itemView) {

    override fun onBindViewHolder(viewTypedListItem: ViewTypedListItem) {
        val item = viewTypedListItem.content as TodoItem
        itemView.todoTV.text = item.text
        itemView.swipeLayout.reset()
//        itemView.swipeLayout.swipeListener = object : SwipeLayout.SwipeListener {
//            override fun swipedToLeft() = delete(adapterPosition)
//            override fun swipedToRight() = toggleDone(adapterPosition)
//        }
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
        itemView.newItemSwipeLayout.reset()
//        itemView.newItemSwipeLayout.swipeListener = object : SwipeLayout.SwipeListener {
//            override fun swipedToLeft() = deleteHeaderItem()
//
//            override fun swipedToRight() { }
//        }
    }

    private fun saveTodoItem(view: View) {
        view.hideKeyboard()
        val todoItemText = itemView.todoEditText.text.toString()
        itemView.todoEditText.text?.clear()
        interaction.saveTodoAndDeleteEditableItem(todoItemText)
    }

}