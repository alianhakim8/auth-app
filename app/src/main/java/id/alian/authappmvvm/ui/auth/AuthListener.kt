package id.alian.authappmvvm.ui.auth

interface AuthListener {
    fun onStarted()

    fun onSuccess()

    fun onError(message: String)
}