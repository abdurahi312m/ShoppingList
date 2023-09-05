package kg.abu.shoppinglist.db

import kg.abu.shoppinglist.entities.ShopListNameItem

interface ShopListNameListener {

    fun deleteItem(id: Int) {}

    fun editItem(shopListNameItem: ShopListNameItem) {}

    fun onClickItem(shopListNameItem: ShopListNameItem) {}

}