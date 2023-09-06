package kg.abu.shoppinglist.db

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.ListNameItemBinding
import kg.abu.shoppinglist.entities.ShopListNameItem

class ShopListNameItemHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ListNameItemBinding.bind(view)

    fun setData(shopListNameItem: ShopListNameItem, listener: ShopListNameListener) =
        with(binding) {
            tvListName.text = shopListNameItem.name
            tvTime.text = shopListNameItem.time
            itemView.setOnClickListener {
                listener.onClickItem(shopListNameItem)
            }
            imDelete.setOnClickListener {
                listener.deleteItem(shopListNameItem.id!!)
            }
            imEdit.setOnClickListener {
                listener.editItem(shopListNameItem)
            }
        }

    companion object {
        fun create(parent: ViewGroup): ShopListNameItemHolder {
            return ShopListNameItemHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_name_item, parent, false)
            )
        }
    }

}