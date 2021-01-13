package pl.paullettuce.dayscountdown.domain.model

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

data class ViewTypedListItem(val viewTypeId: Int, val content: Any?)

class ViewTypedListItemDiffCallback @Inject constructor(): DiffUtil.ItemCallback<ViewTypedListItem>(){
    override fun areItemsTheSame(oldItem: ViewTypedListItem, newItem: ViewTypedListItem): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: ViewTypedListItem,
        newItem: ViewTypedListItem
    ): Boolean {
        return oldItem.content == newItem.content
    }
}