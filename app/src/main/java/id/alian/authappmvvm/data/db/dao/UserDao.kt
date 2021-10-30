package id.alian.authappmvvm.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import id.alian.authappmvvm.data.db.entities.CURRENT_USER_ID
import id.alian.authappmvvm.data.db.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun upsert(user: User): Long

    @Query("SELECT * FROM User WHERE uid = $CURRENT_USER_ID")
    fun getUser(): LiveData<User>

}