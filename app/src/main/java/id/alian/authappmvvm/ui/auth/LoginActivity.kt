package id.alian.authappmvvm.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import id.alian.authappmvvm.R
import id.alian.authappmvvm.databinding.ActivityLoginBinding
import id.alian.authappmvvm.ui.viewmodel.AuthViewModel
import id.alian.authappmvvm.ui.viewmodel.AuthViewModelProviderFactory
import id.alian.authappmvvm.utils.hide
import id.alian.authappmvvm.utils.show
import id.alian.authappmvvm.utils.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    private lateinit var root: View
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel =
            ViewModelProvider(this, AuthViewModelProviderFactory()).get(AuthViewModel::class.java)

        binding.viewModel = viewModel
        viewModel.authListener = this

        progressBar = binding.progressBar
        root = View(this)
    }

    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(loginResponse: LiveData<String>) {
        loginResponse.observe(this, {
            progressBar.hide()
            toast(it)
        })
    }

    override fun onError(message: String) {
        progressBar.hide()
        toast(message)
    }
}