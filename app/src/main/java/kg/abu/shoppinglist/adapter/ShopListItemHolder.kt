package kg.abu.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.ListNameItemBinding
import kg.abu.shoppinglist.entities.ShopListNameItem
import kg.abu.shoppinglist.entities.ShoppingListItem

class ShopListItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ListNameItemBinding.bind(view)

    fun setItemData(shopListItem: ShoppingListItem, listener: ShopListItemListener) =
        with(binding) {}

    fun setLibraryData(shopListItem: ShoppingListItem, listener: ShopListItemListener) =
        with(binding) {}

    companion object {
        fun createShopItem(parent: ViewGroup): ShopListItemHolder {
            return ShopListItemHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.shop_list_item, parent, false)
            )
        }

        fun createLibraryItem(parent: ViewGroup): ShopListItemHolder {
            return ShopListItemHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.shop_library_list_item, parent, false)
            )
        }
    }

}