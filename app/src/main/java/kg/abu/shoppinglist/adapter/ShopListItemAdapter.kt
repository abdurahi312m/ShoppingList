package kg.abu.shoppinglist.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kg.abu.shoppinglist.entities.ShopListItem

class ShopListItemAdapter(private val listener: ShopListItemListener) :
    ListAdapter<ShopListItem, ShopListItemHolder>(ShopListItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListItemHolder {
        return if (viewType == 0) {
            ShopListItemHolder.createShopItem(parent)
        } else {
            ShopListItemHolder.createLibraryItem(parent)
        }
    }

    override fun onBindViewHolder(holder: ShopListItemHolder, position: Int) {
        if (getItem(position).itemType == 0) {
            holder.setItemData(getItem(position), listener)
        } else {
            holder.setLibraryData(getItem(position), listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType
    }

    companion object {
        const val EDIT = 0
        const val CHECK_BOX = 1
        const val EDIT_LIBRARY_ITEM = 2
        const val DELETE_LIBRARY_ITEM = 3
        const val ADD_ITEM = 4
    }

}