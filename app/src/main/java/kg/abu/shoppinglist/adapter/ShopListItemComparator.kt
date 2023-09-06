package kg.abu.shoppinglist.adapter

import androidx.recyclerview.widget.DiffUtil
import kg.abu.shoppinglist.entities.ShopListNameItem
import kg.abu.shoppinglist.entities.ShoppingListItem

class ShopListItemComparator : DiffUtil.ItemCallback<ShoppingListItem>() {

    override fun areItemsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShoppingListItem, newItem: ShoppingListItem): Boolean {
        return oldItem == newItem
    }

}