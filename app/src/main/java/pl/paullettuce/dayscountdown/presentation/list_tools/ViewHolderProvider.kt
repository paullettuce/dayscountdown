package pl.paullettuce.dayscountdown.presentation.list_tools

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.paullettuce.dayscountdown.domain.model.ViewTypedListItem
import java.lang.RuntimeException

abstract class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun onBindViewHolder(viewTypedListItem: ViewTypedListItem)
}

interface ViewHolderFactory {
    fun createInstance(parent: ViewGroup): ViewHolder
}

class ViewHolderProvider {
    private val viewHolderFactories = HashMap<Int, ViewHolderFactory>()

    fun createViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return viewHolderFactories[viewType]?.createInstance(parent)
            ?: throw RuntimeException("ViewHolderFactory not registered for type $viewType")
    }

    fun registerViewHolderFactory(viewType: Int, viewHolderFactory: ViewHolderFactory) {
        viewHolderFactories[viewType] = viewHolderFactory
    }
}