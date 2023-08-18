package kg.abu.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.NoteListItemBinding
import kg.abu.shoppinglist.entities.NoteItem

class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = NoteListItemBinding.bind(view)

    fun setData(note: NoteItem) = with(binding) {
        tvTitle.text = note.title
        tvDescripttion.text = note.content
        tvTime.text = note.time
    }

    companion object {
        fun create(parent: ViewGroup): ItemHolder {
            return ItemHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.note_list_item, parent, false)
            )
        }
    }

}