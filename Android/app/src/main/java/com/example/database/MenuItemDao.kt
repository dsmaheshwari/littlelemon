package com.example.database

import android.content.Context
import androidx.room.*
import com.example.littlelemon.home.MenuItemList
import kotlinx.coroutines.flow.Flow

@Entity
data class MenuItem(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
)

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM MenuItem")
    fun getAll(): Flow<List<MenuItem>>

    @Insert
    fun insertAll(vararg menuItems: MenuItem)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItem) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuItemDatabase : RoomDatabase() {
    abstract fun menuItemDao(): MenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: MenuItemDatabase? = null

        fun getDatabase(context: Context): MenuItemDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MenuItemDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}