package kg.abu.shoppinglist.activities

import android.app.Application
import kg.abu.shoppinglist.db.MainDatabase

class YourMainApp : Application() {

    val database by lazy { MainDatabase.getDatabase(this) }

}