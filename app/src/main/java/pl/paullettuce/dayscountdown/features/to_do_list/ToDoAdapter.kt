package pl.paullettuce.dayscountdown.features.to_do_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_to_do.view.*
import pl.paullettuce.dayscountdown.R
import pl.paullettuce.dayscountdown.data.ToDoItem

class ToDoAdapter(private var items: List<ToDoItem> = emptyList()): RecyclerView.Adapter<ToDoItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        return ToDoItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_to_do, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    fun setItems(items: List<ToDoItem>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class ToDoItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindView(item: ToDoItem) {
        itemView.todoTV.text = item.text
    }
}