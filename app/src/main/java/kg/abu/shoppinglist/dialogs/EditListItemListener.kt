package kg.abu.shoppinglist.dialogs

import kg.abu.shoppinglist.entities.ShopListItem

interface EditListItemListener {

    fun onClick(item: ShopListItem) {
    }

}