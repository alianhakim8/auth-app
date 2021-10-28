package id.alian.authappmvvm.data.db.entities

import androidx.room.*;

const val CURRENT_USER_ID = 0

@Entity
data class User(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val password: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}