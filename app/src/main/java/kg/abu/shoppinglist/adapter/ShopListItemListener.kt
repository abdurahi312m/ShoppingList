package kg.abu.shoppinglist.adapter

import kg.abu.shoppinglist.entities.ShopListNameItem

interface ShopListItemListener {

    fun deleteItem(id: Int) {}

    fun editItem(shopListNameItem: ShopListNameItem) {}

    fun onClickItem(shopListNameItem: ShopListNameItem) {}

}