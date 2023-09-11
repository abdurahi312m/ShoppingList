package kg.abu.shoppinglist.adapter

import kg.abu.shoppinglist.entities.ShopListItem
import kg.abu.shoppinglist.entities.ShopListNameItem

interface ShopListItemListener {

    fun onClickItem(shopListItem: ShopListItem, state: Int) {}

}