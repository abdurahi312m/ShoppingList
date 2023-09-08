package kg.abu.shoppinglist.adapter

import kg.abu.shoppinglist.entities.ShopListItem
import kg.abu.shoppinglist.entities.ShopListNameItem

interface ShopListItemListener {

    fun deleteItem(id: Int) {}

    fun onClickItem(shopListItem: ShopListItem) {}

}