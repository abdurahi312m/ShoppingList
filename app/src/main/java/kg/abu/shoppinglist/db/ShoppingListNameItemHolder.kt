package kg.abu.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.ListNameItemBinding
import kg.abu.shoppinglist.databinding.NoteListItemBinding
import kg.abu.shoppinglist.entities.NoteItem
import kg.abu.shoppinglist.entities.ShoppingListName
import kg.abu.shoppinglist.utils.HtmlManager

class ShoppingListNameItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ListNameItemBinding.bind(view)

    fun setData(shopListNameItem: ShoppingListName, listener: ShopListNameListener) = with(binding) {
        tvListName.text = shopListNameItem.name
        tvTime.text = shopListNameItem.time
        itemView.setOnClickListener {}
        imDelete.setOnClickListener {
            listener.deleteItem(shopListNameItem.id!!)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ShoppingListNameItemHolder {
            return ShoppingListNameItemHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_name_item, parent, false)
            )
        }
    }

}