package id.alian.authappmvvm.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {
    fun onStarted()

    fun onSuccess(loginResponse: LiveData<String>)

    fun onError(message: String)
}