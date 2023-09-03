package kg.abu.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.ActivityMainBinding
import kg.abu.shoppinglist.dialogs.Listener
import kg.abu.shoppinglist.dialogs.NewListDialog
import kg.abu.shoppinglist.fragments.FragmentManager
import kg.abu.shoppinglist.fragments.NoteFragment
import kg.abu.shoppinglist.fragments.ShopListNamesFragment

class MainActivity : AppCompatActivity(), Listener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
        setBottomNavListener()
    }

    private fun setBottomNavListener() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.settings -> {
                    Log.d("MyTAG", "setBottomNavListener: settings")
                }

                R.id.notes -> {
                    FragmentManager.setFragment(NoteFragment.newInstance(), this)
                }

                R.id.shop_list -> {
                    FragmentManager.setFragment(ShopListNamesFragment.newInstance(), this)
                }

                R.id.new_item -> {
                    FragmentManager.currentFragment?.onClickNew()
                }
            }
            true
        }
    }

    override fun onClick(name: String) {
        Log.d("TAG", "onClick: $name ")
    }
}