package kg.abu.shoppinglist.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnActionExpandListener
import android.view.View
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.adapter.ShopListItemAdapter
import kg.abu.shoppinglist.adapter.ShopListItemListener
import kg.abu.shoppinglist.databinding.ActivityShopListBinding
import kg.abu.shoppinglist.db.MainViewModel
import kg.abu.shoppinglist.db.MainViewModelFactory
import kg.abu.shoppinglist.dialogs.EditListItemDialog
import kg.abu.shoppinglist.dialogs.EditListItemListener
import kg.abu.shoppinglist.entities.LibraryItem
import kg.abu.shoppinglist.entities.ShopListItem
import kg.abu.shoppinglist.entities.ShopListNameItem
import kg.abu.shoppinglist.utils.ShareHelper

class ShopListActivity : AppCompatActivity(), ShopListItemListener {
    private lateinit var binding: ActivityShopListBinding
    private var shopListNameItem: ShopListNameItem? = null
    private lateinit var saveItem: MenuItem
    private var edItem: EditText? = null
    private var adapter: ShopListItemAdapter? = null
    private lateinit var textWatcher: TextWatcher

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((applicationContext as YourMainApp).database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initRcView()
        listItemObserver()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shop_list_menu, menu)
        saveItem = menu?.findItem(R.id.save_item)!!
        val newItem = menu.findItem(R.id.new_item)
        edItem = newItem.actionView?.findViewById(R.id.edNewShopItem) as EditText
        newItem.setOnActionExpandListener(expandActionView())
        saveItem.isVisible = false
        textWatcher = textWatcher()
        return true
    }

    private fun textWatcher(): TextWatcher {
        return object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("MyTAG", "onTextChanged: $s")
                mainViewModel.getAllLibraryItems("%$s%")
            }

            override fun afterTextChanged(s: Editable?) {

            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_item -> {
                adNewShopItem()
            }

            R.id.delete_list -> {
                mainViewModel.deleteShopList(shopListNameItem?.id!!, true)
                finish()
            }

            R.id.clear_list -> {
                mainViewModel.deleteShopList(shopListNameItem?.id!!, false)
                finish()
            }

            R.id.share_list -> {
                startActivity(
                    Intent.createChooser(
                        ShareHelper.shareShopList(
                            adapter?.currentList!!,
                            shopListNameItem?.name!!
                        ),
                        "Share by"
                    )
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun adNewShopItem() {
        if (edItem?.text.toString().isEmpty()) return
        val item = ShopListItem(
            null,
            edItem?.text.toString(),
            "",
            false,
            shopListNameItem?.id!!,
            0
        )
        edItem?.setText("")
        mainViewModel.insertShopItem(item)
    }

    private fun listItemObserver() {
        mainViewModel.getAllItemsFromList(shopListNameItem?.id!!).observe(this) {
            adapter?.submitList(it)
            binding.tvEmpty.visibility = if (it.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun libraryItemObserver() {
        mainViewModel.libraryItems.observe(this) {
            val tempShopList = ArrayList<ShopListItem>()
            it.forEach { item ->
                val shopItem = ShopListItem(
                    item.id,
                    item.name,
                    "",
                    false,
                    0,
                    1
                )
                tempShopList.add(shopItem)
            }
            adapter?.submitList(tempShopList)
            binding.tvEmpty.visibility = if (it.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }

    private fun initRcView() = with(binding) {
        adapter = ShopListItemAdapter(this@ShopListActivity)
        rcView.layoutManager = LinearLayoutManager(this@ShopListActivity)
        rcView.adapter = adapter
    }

    private fun expandActionView(): OnActionExpandListener {
        return object : OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                saveItem.isVisible = true
                edItem?.addTextChangedListener(textWatcher)
                libraryItemObserver()
                mainViewModel.getAllItemsFromList(shopListNameItem?.id!!)
                    .removeObservers(this@ShopListActivity)
                mainViewModel.getAllLibraryItems("%%")
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                saveItem.isVisible = false
                edItem?.removeTextChangedListener(textWatcher)
                invalidateOptionsMenu()
                mainViewModel.libraryItems.removeObservers(this@ShopListActivity)
                edItem?.setText("")
                listItemObserver()
                return true
            }
        }
    }

    private fun init() {
        shopListNameItem = intent.getSerializableExtra(SHOP_LIST_NAME) as ShopListNameItem
    }

    companion object {
        const val SHOP_LIST_NAME = "shopListName"
    }

    override fun onClickItem(shopListItem: ShopListItem, state: Int) {
        when (state) {
            ShopListItemAdapter.CHECK_BOX -> {
                mainViewModel.updateListItem(shopListItem)
            }

            ShopListItemAdapter.EDIT -> {
                editListItem(shopListItem)
            }

            ShopListItemAdapter.EDIT_LIBRARY_ITEM -> {
                editLibraryItem(shopListItem)
            }

            ShopListItemAdapter.DELETE_LIBRARY_ITEM -> {
                mainViewModel.deleteLibraryItem(shopListItem.id!!)
                mainViewModel.getAllLibraryItems("%${edItem?.text.toString()}%")
            }
        }
    }

    private fun editListItem(item: ShopListItem) {
        EditListItemDialog.showDialog(this, item, object : EditListItemListener {
            override fun onClick(item: ShopListItem) {
                mainViewModel.updateListItem(item)
            }
        })
    }

    private fun editLibraryItem(item: ShopListItem) {
        EditListItemDialog.showDialog(this, item, object : EditListItemListener {
            override fun onClick(item: ShopListItem) {
                mainViewModel.updateLibraryItem(LibraryItem(item.id, item.name, item.itemInfo))
                mainViewModel.getAllLibraryItems("%${edItem?.text.toString()}%")
            }
        })
    }
}