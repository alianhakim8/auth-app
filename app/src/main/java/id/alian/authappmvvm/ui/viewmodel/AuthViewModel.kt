package id.alian.authappmvvm.ui.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.alian.authappmvvm.data.db.entities.User
import id.alian.authappmvvm.data.repository.UserRepository
import id.alian.authappmvvm.ui.auth.AuthListener
import id.alian.authappmvvm.ui.auth.LoginActivity
import id.alian.authappmvvm.ui.auth.SignUpActivity
import id.alian.authappmvvm.utils.ApiException
import id.alian.authappmvvm.utils.Coroutines
import id.alian.authappmvvm.utils.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    var authListener: AuthListener? = null

    fun onLoginButtonClick(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onError("Email or password cannot empty")
        } else {
//            Coroutines.main {
//                try {
//                    val authResponse = repository.login(email!!, password!!)
//                    authResponse.user?.let {
//                        authListener?.onSuccess(authResponse.user)
//                        repository.saveUser(it)
//                    }
//                    authListener?.onError(authResponse.message!!)
//                } catch (e: ApiException) {
//                    authListener?.onError(e.message!!)
//                } catch (e: NoInternetException) {
//                    authListener?.onError(e.message!!)
//                }
//            }
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val authResponse = repository.login(email!!, password!!)
                    authResponse.user?.let {
                        authListener?.onSuccess(authResponse.user)
                        repository.saveUser(it)
                    }
                    authListener?.onError(authResponse.message!!)
                } catch (e: ApiException) {
                    authListener?.onError(e.message!!)
                } catch (e: NoInternetException) {
                    authListener?.onError(e.message!!)
                }
            }
        }
    }

    fun onSignUpButtonClick(view: View) {
        val newUser = User(null, name, email, password)
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onError("cannot be empty")
        } else if (!password.equals(confirmPassword)) {
            authListener?.onError("password not match")
        } else {
//            Coroutines.main {
//                try {
//                    val authResponse = repository.login(email!!, password!!)
//                    authResponse.user?.let {
//                        authListener?.onSuccess(authResponse.user)
//                        repository.saveUser(it)
//                    }
//                    authListener?.onError(authResponse.message!!)
//                } catch (e: ApiException) {
//                    authListener?.onError(e.message!!)
//                } catch (e: NoInternetException) {
//                    authListener?.onError(e.message!!)
//                }
//            }
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    try {
                        val authResponse = repository.signUp(newUser)
                        authResponse.user?.let {
                            authListener?.onSuccess(authResponse.user)
                            repository.saveUser(it)
                        }
                        authListener?.onError(authResponse.message!!)
                    } catch (e: ApiException) {
                        authListener?.onError(e.message!!)
                    } catch (e: NoInternetException) {
                        authListener?.onError(e.message!!)
                    }
                } catch (e: ApiException) {
                    authListener?.onError(e.message!!)
                } catch (e: NoInternetException) {
                    authListener?.onError(e.message!!)
                }
            }
        }
    }

    fun onSignUp(view: View) {
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onSignIn(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun loginSession() = repository.getUser()
}