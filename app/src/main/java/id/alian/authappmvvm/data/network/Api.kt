package id.alian.authappmvvm.data.network

import id.alian.authappmvvm.data.network.responses.AuthResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    companion object {
        // same as calling :
        // Api()
        operator fun invoke(): Api {
            return Retrofit.Builder()
                .baseUrl("http://192.168.1.11:8080/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
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


}

