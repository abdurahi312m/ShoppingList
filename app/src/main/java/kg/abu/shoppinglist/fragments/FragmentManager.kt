package kg.abu.shoppinglist.fragments

import androidx.appcompat.app.AppCompatActivity
import kg.abu.shoppinglist.R

object FragmentManager {

    private var currentFragment: BaseFragment? = null

    fun setFragment(newFragment: BaseFragment, activity: AppCompatActivity) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.placeHolder, newFragment)
        transaction.commit()
        currentFragment = newFragment
    }

}