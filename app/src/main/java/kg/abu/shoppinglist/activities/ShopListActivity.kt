package kg.abu.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import kg.abu.shoppinglist.entities.ShopListItem
import kg.abu.shoppinglist.entities.ShopListNameItem

class ShopListActivity : AppCompatActivity(), ShopListItemListener {
    private lateinit var binding: ActivityShopListBinding
    private var shopListNameItem: ShopListNameItem? = null
    private lateinit var saveItem: MenuItem
    private var edItem: EditText? = null
    private var adapter: ShopListItemAdapter? = null

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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_item) {
            adNewShopItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun adNewShopItem() {
        if (edItem?.text.toString().isEmpty()) return
        val item = ShopListItem(
            null,
            edItem?.text.toString(),
            null,
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

    private fun initRcView() = with(binding) {
        adapter = ShopListItemAdapter(this@ShopListActivity)
        rcView.layoutManager = LinearLayoutManager(this@ShopListActivity)
        rcView.adapter = adapter
    }

    private fun expandActionView(): OnActionExpandListener {
        return object : OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                saveItem.isVisible = true
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                saveItem.isVisible = false
                invalidateOptionsMenu()
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

    override fun deleteItem(id: Int) {

    }

    override fun onClickItem(shopListItem: ShopListItem) {
        mainViewModel.updateListItem(shopListItem)
    }
}