package kg.abu.shoppinglist.db

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kg.abu.shoppinglist.entities.ShopListNameItem

class ShopListNameAdapter(private val listener: ShopListNameListener) :
    ListAdapter<ShopListNameItem, ShopListNameItemHolder>(ShopListNameItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListNameItemHolder {
        return ShopListNameItemHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ShopListNameItemHolder, position: Int) {
        holder.setData(getItem(position), listener)
    }

}