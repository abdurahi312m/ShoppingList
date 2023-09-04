package kg.abu.shoppinglist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kg.abu.shoppinglist.activities.YourMainApp
import kg.abu.shoppinglist.databinding.FragmentShopListNamesBinding
import kg.abu.shoppinglist.db.MainViewModel
import kg.abu.shoppinglist.db.ShopListNameAdapter
import kg.abu.shoppinglist.db.ShopListNameListener
import kg.abu.shoppinglist.dialogs.DeleteDialog
import kg.abu.shoppinglist.dialogs.DeleteListener
import kg.abu.shoppinglist.dialogs.Listener
import kg.abu.shoppinglist.dialogs.NewListDialog
import kg.abu.shoppinglist.entities.NoteItem
import kg.abu.shoppinglist.entities.ShoppingListName
import kg.abu.shoppinglist.utils.TimeManager

class ShopListNamesFragment : BaseFragment(), ShopListNameListener {
    private lateinit var binding: FragmentShopListNamesBinding
    private lateinit var adapter: ShopListNameAdapter

    private val mainViewModel: MainViewModel by activityViewModels {
        MainViewModel.MainViewModelFactory((context?.applicationContext as YourMainApp).database)
    }

    override fun onClickNew() {
        NewListDialog.showDialog(activity as AppCompatActivity, object : Listener {
            override fun onClick(name: String) {
                val shopListName = ShoppingListName(
                    null,
                    name,
                    TimeManager.getCurrentTime(),
                    0,
                    0,
                    ""
                )
                mainViewModel.insertShopListName(shopListName)
            }
        }, "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopListNamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        observer()
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = ShopListNameAdapter(this@ShopListNamesFragment)
        rcView.adapter = adapter
    }

    private fun observer() {
        mainViewModel.allShopListNames.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ShopListNamesFragment()
    }

    override fun deleteItem(id: Int) {
        DeleteDialog.showDialog(context as AppCompatActivity, object : DeleteListener{
            override fun onClick() {
                mainViewModel.deleteShopListName(id)
            }
        })
    }

    override fun editItem(shopListName: ShoppingListName) {
        NewListDialog.showDialog(activity as AppCompatActivity, object : Listener {
            override fun onClick(name: String) {
                mainViewModel.updateListName(shopListName.copy(name = name))
            }
        }, shopListName.name)
    }

    override fun onClickItem(shopListName: ShoppingListName) {

    }
}