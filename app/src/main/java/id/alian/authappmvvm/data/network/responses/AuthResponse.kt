package id.alian.authappmvvm.data.network.responses

import id.alian.authappmvvm.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)
