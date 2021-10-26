package id.alian.authappmvvm.ui.auth

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.alian.authappmvvm.R
import id.alian.authappmvvm.databinding.ActivityLoginBinding
import id.alian.authappmvvm.ui.viewmodel.AuthViewModel
import id.alian.authappmvvm.ui.viewmodel.AuthViewModelProviderFactory
import id.alian.authappmvvm.utils.snackBar
import id.alian.authappmvvm.utils.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    private lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel =
            ViewModelProvider(this, AuthViewModelProviderFactory()).get(AuthViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.authListener = this
        root = View(this)
    }

    override fun onStarted() {
        toast("on started")

    }

    override fun onSuccess() {
        toast("on success")
    }

    override fun onError(message: String) {
        toast("on error")
    }
}