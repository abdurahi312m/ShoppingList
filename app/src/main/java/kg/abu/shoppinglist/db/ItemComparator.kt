package kg.abu.shoppinglist.db

import androidx.recyclerview.widget.DiffUtil
import kg.abu.shoppinglist.entities.NoteItem

class ItemComparator : DiffUtil.ItemCallback<NoteItem>() {

    override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
        return oldItem == newItem
    }

}