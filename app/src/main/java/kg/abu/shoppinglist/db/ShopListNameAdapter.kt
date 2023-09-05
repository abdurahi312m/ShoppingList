package kg.abu.shoppinglist.db

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kg.abu.shoppinglist.entities.ShopListNameItem

class ShopListNameAdapter(private val listener: ShopListNameListener) :
    ListAdapter<ShopListNameItem, ShoppingListNameItemHolder>(ShopListNameItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListNameItemHolder {
        return ShoppingListNameItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ShoppingListNameItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

}