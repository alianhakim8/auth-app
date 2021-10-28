package id.alian.authappmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.alian.authappmvvm.data.db.dao.UserDao
import id.alian.authappmvvm.data.db.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        // volatile : visible to all threads
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) {
            if (instance != null) {
                instance
            } else {
                synchronized(LOCK) {
                    if (instance != null) {
                        buildDatabase(context).also {
                            instance = it
                        }
                    }
                }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "auth.db"
            ).build()
    }
}