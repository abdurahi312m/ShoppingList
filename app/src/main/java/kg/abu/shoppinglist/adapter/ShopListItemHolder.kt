package kg.abu.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.ListNameItemBinding
import kg.abu.shoppinglist.databinding.ShopListItemBinding
import kg.abu.shoppinglist.entities.ShopListItem

class ShopListItemHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun setItemData(shopListItem: ShopListItem, listener: ShopListItemListener) {
        val binding = ShopListItemBinding.bind(view)
        binding.apply {
            tvName.text = shopListItem.name
        }
    }

    fun setLibraryData(shopListItem: ShopListItem, listener: ShopListItemListener) {}

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