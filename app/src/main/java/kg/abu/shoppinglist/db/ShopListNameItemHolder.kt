package kg.abu.shoppinglist.db

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
            pBar.max = shopListNameItem.allItemCounter
            pBar.progress = shopListNameItem.checkedItemsCounter
            val colorState = ColorStateList.valueOf(getProgressColorState(shopListNameItem, binding.root.context))
            pBar.progressTintList = colorState
            counterCard.backgroundTintList = colorState
            val counterText =
                "${shopListNameItem.checkedItemsCounter}/${shopListNameItem.allItemCounter}"
            tvCounter.text = counterText
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

    private fun getProgressColorState(item: ShopListNameItem, context: Context): Int {
        return if (item.checkedItemsCounter == item.allItemCounter) {
            ContextCompat.getColor(context, R.color.picker_yellow)
        } else {
            ContextCompat.getColor(context, R.color.red_main)
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