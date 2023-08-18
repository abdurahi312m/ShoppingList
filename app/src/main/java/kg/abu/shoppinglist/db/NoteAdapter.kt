package kg.abu.shoppinglist.db

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kg.abu.shoppinglist.entities.NoteItem

class NoteAdapter : ListAdapter<NoteItem, ItemHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.setData(getItem(position))
    }

}