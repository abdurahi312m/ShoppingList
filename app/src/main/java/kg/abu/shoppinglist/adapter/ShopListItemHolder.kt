package kg.abu.shoppinglist.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.adapter.ShopListItemAdapter.Companion.ADD_ITEM
import kg.abu.shoppinglist.adapter.ShopListItemAdapter.Companion.CHECK_BOX
import kg.abu.shoppinglist.adapter.ShopListItemAdapter.Companion.DELETE_LIBRARY_ITEM
import kg.abu.shoppinglist.adapter.ShopListItemAdapter.Companion.EDIT
import kg.abu.shoppinglist.adapter.ShopListItemAdapter.Companion.EDIT_LIBRARY_ITEM
import kg.abu.shoppinglist.databinding.ShopLibraryListItemBinding
import kg.abu.shoppinglist.databinding.ShopListItemBinding
import kg.abu.shoppinglist.entities.ShopListItem

class ShopListItemHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun setItemData(shopListItem: ShopListItem, listener: ShopListItemListener) {
        val binding = ShopListItemBinding.bind(view)
        binding.apply {
            tvName.text = shopListItem.name
            tvInfo.text = shopListItem.itemInfo
            tvInfo.visibility = infoVisibility(shopListItem)
            checkBox.isChecked = shopListItem.itemChecked
            setPaintFlagAndColor(binding)
            checkBox.setOnClickListener {
                listener.onClickItem(shopListItem.copy(itemChecked = checkBox.isChecked), CHECK_BOX)
            }
            imEdit.setOnClickListener {
                listener.onClickItem(shopListItem, EDIT)
            }
        }
    }

    fun setLibraryData(shopListItem: ShopListItem, listener: ShopListItemListener) {
        val binding = ShopLibraryListItemBinding.bind(view)
        binding.apply {
            tvName.text = shopListItem.name
            imEdit.setOnClickListener {
                listener.onClickItem(shopListItem, EDIT_LIBRARY_ITEM)
            }
            imDelete.setOnClickListener {
                listener.onClickItem(shopListItem, DELETE_LIBRARY_ITEM)
            }
            itemView.setOnClickListener {
                listener.onClickItem(shopListItem, ADD_ITEM)
            }
        }
    }

    private fun setPaintFlagAndColor(binding: ShopListItemBinding) {
        binding.apply {
            if (checkBox.isChecked) {
                tvName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tvName.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_light))
                tvInfo.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                tvInfo.setTextColor(ContextCompat.getColor(binding.root.context, R.color.gray_light))
            } else {
                tvName.paintFlags = Paint.ANTI_ALIAS_FLAG
                tvInfo.paintFlags = Paint.ANTI_ALIAS_FLAG
            }
        }
    }

    private fun infoVisibility(shopListItem: ShopListItem): Int {
        return if (shopListItem.itemInfo.isEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    companion object {
        fun createShopItem(parent: ViewGroup): ShopListItemHolder {
            return ShopListItemHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.shop_list_item, parent, false)
            )
        }

        fun createLibraryItem(parent: ViewGroup): ShopListItemHolder {
            return ShopListItemHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.shop_library_list_item, parent, false)
            )
        }
    }

}