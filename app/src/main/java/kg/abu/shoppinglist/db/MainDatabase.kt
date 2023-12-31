package kg.abu.shoppinglist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kg.abu.shoppinglist.entities.LibraryItem
import kg.abu.shoppinglist.entities.NoteItem
import kg.abu.shoppinglist.entities.ShopListItem
import kg.abu.shoppinglist.entities.ShopListNameItem

@Database(
    entities = [
        LibraryItem::class,
        NoteItem::class,
        ShopListItem::class,
        ShopListNameItem::class
    ],
    version = 1,
    /*exportSchema = true,
    autoMigrations = [AutoMigration(from = 2, to = 3)]*/
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null
        fun getDatabase(context: Context): MainDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "shopping_list.db"
                ).build()
                instance
            }
        }
    }

}