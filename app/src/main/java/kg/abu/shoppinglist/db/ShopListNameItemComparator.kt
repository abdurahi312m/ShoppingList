package kg.abu.shoppinglist.db

import androidx.recyclerview.widget.DiffUtil
import kg.abu.shoppinglist.entities.NoteItem
import kg.abu.shoppinglist.entities.ShoppingListName

class ShopListNameItemComparator : DiffUtil.ItemCallback<ShoppingListName>() {

    override fun areItemsTheSame(oldItem: ShoppingListName, newItem: ShoppingListName): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShoppingListName, newItem: ShoppingListName): Boolean {
        return oldItem == newItem
    }

}