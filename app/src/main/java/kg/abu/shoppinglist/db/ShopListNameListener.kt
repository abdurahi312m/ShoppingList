package kg.abu.shoppinglist.db

import kg.abu.shoppinglist.entities.NoteItem

interface ShopListNameListener {

    fun deleteItem(id: Int) {}

    fun onClickItem(note: NoteItem) {}

}