package id.alian.authappmvvm.ui.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.alian.authappmvvm.data.repository.UserRepository
import id.alian.authappmvvm.ui.auth.AuthListener
import id.alian.authappmvvm.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onError("Invalid email or password")
        } else {
            Coroutines.main {
                val response = UserRepository().login(email!!, password!!)
                if (response.isSuccessful) {
                    authListener?.onSuccess(response.body()?.user!!)
                } else {
                    authListener?.onError("error code : ${response.code()}")
                }
            }
        }
    }
}