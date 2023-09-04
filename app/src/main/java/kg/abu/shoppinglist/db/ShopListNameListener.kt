package kg.abu.shoppinglist.db

import kg.abu.shoppinglist.entities.ShoppingListName

interface ShopListNameListener {

    fun deleteItem(id: Int) {}

    fun editItem(shopListName: ShoppingListName) {}

    fun onClickItem(shopListName: ShoppingListName) {}

}