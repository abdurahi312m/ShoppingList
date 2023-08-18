package kg.abu.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kg.abu.shoppinglist.R
import kg.abu.shoppinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavListener()
    }

    private fun setBottomNavListener() {
        binding.bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.settings -> {
                    Log.d("MyTAG", "setBottomNavListener: settings")
                }

                R.id.notes -> {
                    Log.d("MyTAG", "setBottomNavListener: notes")
                }

                R.id.shop_list -> {
                    Log.d("MyTAG", "setBottomNavListener: shop list")
                }

                R.id.new_item -> {
                    Log.d("MyTAG", "setBottomNavListener: new item")
                }
            }
            true
        }
    }
}