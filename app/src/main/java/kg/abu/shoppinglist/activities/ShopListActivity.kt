package kg.abu.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnActionExpandListener
import androidx.activity.viewModels
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.ActivityShopListBinding
import kg.abu.shoppinglist.db.MainViewModel
import kg.abu.shoppinglist.db.MainViewModelFactory
import kg.abu.shoppinglist.entities.ShopListNameItem

class ShopListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopListBinding
    private var shopListNameItem: ShopListNameItem? = null
    private lateinit var saveItem: MenuItem

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((applicationContext as YourMainApp).database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shop_list_menu, menu)
        saveItem = menu?.findItem(R.id.save_item)!!
        val newItem = menu.findItem(R.id.new_item)
        newItem.setOnActionExpandListener(expandActionView())
        saveItem.isVisible = false
        return true
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
        binding.tvTest.text = shopListNameItem?.name
    }

    companion object {
        const val SHOP_LIST_NAME = "shopListName"
    }
}