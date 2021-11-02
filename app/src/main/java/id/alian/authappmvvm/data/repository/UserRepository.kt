package id.alian.authappmvvm.data.repository

import id.alian.authappmvvm.data.network.Api
import id.alian.authappmvvm.data.network.SafeApiRequest
import id.alian.authappmvvm.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository : SafeApiRequest() {

    suspend fun login(email: String, password: String): AuthResponse {
        return apiRequest {
            Api().login(email, password)
        }
    }
}