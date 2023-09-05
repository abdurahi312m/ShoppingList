package kg.abu.shoppinglist.db

import androidx.recyclerview.widget.DiffUtil
import kg.abu.shoppinglist.entities.ShopListNameItem

class ShopListNameItemComparator : DiffUtil.ItemCallback<ShopListNameItem>() {

    override fun areItemsTheSame(oldItem: ShopListNameItem, newItem: ShopListNameItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopListNameItem, newItem: ShopListNameItem): Boolean {
        return oldItem == newItem
    }

}