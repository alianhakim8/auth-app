package id.alian.authappmvvm.ui.auth

import androidx.lifecycle.LiveData
import id.alian.authappmvvm.data.db.entities.User

interface AuthListener {
    fun onStarted()

    fun onSuccess(user: User)

    fun onError(message: String)
}