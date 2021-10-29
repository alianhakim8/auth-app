package id.alian.authappmvvm.data.repository

import id.alian.authappmvvm.data.network.Api
import id.alian.authappmvvm.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository {

    suspend fun login(email: String, password: String): Response<AuthResponse> {
        // bad practice
        return Api().login(email, password)
    }
}