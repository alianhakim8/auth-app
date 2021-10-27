package id.alian.authappmvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.alian.authappmvvm.data.network.Api
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun login(email: String, password: String): LiveData<String> {
        val loginResponse = MutableLiveData<String>()

        // this is bad practice, use DI to solve this
        Api().login(email, password).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    loginResponse.value = response.body().toString()
                } else {
                    loginResponse.value = response.errorBody().toString()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginResponse.value = t.message
            }
        })
        return loginResponse
    }
}