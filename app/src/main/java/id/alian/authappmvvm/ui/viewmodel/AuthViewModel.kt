package id.alian.authappmvvm.ui.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import id.alian.authappmvvm.data.repository.UserRepository
import id.alian.authappmvvm.ui.auth.AuthListener

class AuthViewModel : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onError("Invalid email or password")
        } else {
            val loginResponse = UserRepository().login(email!!, password!!)
            authListener?.onSuccess(loginResponse)
        }
    }
}