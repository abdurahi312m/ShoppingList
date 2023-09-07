package kg.abu.shoppinglist.adapter

import androidx.recyclerview.widget.DiffUtil
import kg.abu.shoppinglist.entities.ShopListItem

class ShopListItemComparator : DiffUtil.ItemCallback<ShopListItem>() {

    override fun areItemsTheSame(oldItem: ShopListItem, newItem: ShopListItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopListItem, newItem: ShopListItem): Boolean {
        return oldItem == newItem
    }

}