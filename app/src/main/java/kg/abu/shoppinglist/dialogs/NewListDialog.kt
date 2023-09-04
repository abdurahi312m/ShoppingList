package kg.abu.shoppinglist.dialogs

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.NewListDialogBinding

object NewListDialog {

    fun showDialog(context: Context, listener: Listener, name: String) {
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(context)
        val binding = NewListDialogBinding.inflate(LayoutInflater.from(context))
        builder.setView(binding.root)
        binding.apply {
            edNewLIstName.setText(name)
            if (name.isNotEmpty()) {
                bCreate.text = context.getString(R.string.update)
                textView.text = context.getString(R.string.update_list_name)
            }
            bCreate.setOnClickListener {
                val listName = edNewLIstName.text.toString()
                if (listName.isNotEmpty()) {
                    listener.onClick(listName)
                }
                dialog?.dismiss()
            }
        }
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(null)
        dialog.show()
    }

}