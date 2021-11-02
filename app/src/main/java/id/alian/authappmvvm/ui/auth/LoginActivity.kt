package id.alian.authappmvvm.ui.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import id.alian.authappmvvm.R
import id.alian.authappmvvm.data.db.entities.User
import id.alian.authappmvvm.databinding.ActivityLoginBinding
import id.alian.authappmvvm.ui.viewmodel.AuthViewModel
import id.alian.authappmvvm.ui.viewmodel.AuthViewModelProviderFactory
import id.alian.authappmvvm.utils.hide
import id.alian.authappmvvm.utils.show
import id.alian.authappmvvm.utils.snackbar
import id.alian.authappmvvm.utils.toast

class LoginActivity : AppCompatActivity(), AuthListener {

    private lateinit var root: View
    private lateinit var progressBar: ProgressBar
    private lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel =
            ViewModelProvider(this, AuthViewModelProviderFactory()).get(AuthViewModel::class.java)

        binding.viewModel = viewModel
        viewModel.authListener = this

        rootLayout = binding.rootLayout
        progressBar = binding.progressBar
        root = View(this)
    }

    override fun onStarted() {
        progressBar.show()
    }

    @SuppressLint("ShowToast")
    override fun onSuccess(user: User) {
        rootLayout.snackbar("${user.name} is logged in")
    }

    override fun onError(message: String) {
        progressBar.hide()
        rootLayout.snackbar(message)
    }

    // commit message : handling api exceptions, replace toast with snackbar
}