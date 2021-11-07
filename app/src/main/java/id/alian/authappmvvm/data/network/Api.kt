package id.alian.authappmvvm.data.network

import id.alian.authappmvvm.data.db.entities.User
import id.alian.authappmvvm.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    companion object {
        // same as calling :
        // Api()
        operator fun invoke(
            interceptor: NetworkConnectionInterceptor
        ): Api {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl("http://192.168.1.11:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(Api::class.java)
        }
    }

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>

    @FormUrlEncoded
    @POST("signup")
    suspend fun signUp(
        @Body user: User
    ): Response<AuthResponse>

}

