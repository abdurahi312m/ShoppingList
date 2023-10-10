package kg.abu.shoppinglist.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.EditListItemDialogBinding
import kg.abu.shoppinglist.databinding.NewListDialogBinding
import kg.abu.shoppinglist.entities.ShopListItem

object EditListItemDialog {

    fun showDialog(context: Context, item: ShopListItem, listener: EditListItemListener) {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = EditListItemDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            edName.setText(item.name)
            edInfo.setText(item.itemInfo)
            if (item.itemType == 1) edInfo.visibility = View.GONE
            bUpdate.setOnClickListener {
                if (edName.text.toString().isNotEmpty()) {
                    listener.onClick(
                        item.copy(
                            name = edName.text.toString(),
                            itemInfo = edInfo.text.toString()
                        )
                    )
                }
                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }

}