package id.alian.authappmvvm.data.repository

import id.alian.authappmvvm.data.db.AppDatabase
import id.alian.authappmvvm.data.db.entities.User
import id.alian.authappmvvm.data.network.Api
import id.alian.authappmvvm.data.network.SafeApiRequest
import id.alian.authappmvvm.data.network.responses.AuthResponse

class UserRepository(
    private val db: AppDatabase,
    private val api: Api
) : SafeApiRequest() {

    suspend fun login(email: String, password: String): AuthResponse {
        return apiRequest {
            api.login(email, password)
        }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    suspend fun signUp(user: User): AuthResponse {
        return apiRequest {
            api.signUp(user)
        }
    }

    fun getUser() = db.getUserDao().getUser()
}