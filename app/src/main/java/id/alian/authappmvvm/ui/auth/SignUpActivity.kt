package id.alian.authappmvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.alian.authappmvvm.R
import id.alian.authappmvvm.data.db.entities.User
import id.alian.authappmvvm.databinding.ActivitySignUpBinding
import id.alian.authappmvvm.ui.home.HomeActivity
import id.alian.authappmvvm.ui.viewmodel.AuthViewModel
import id.alian.authappmvvm.ui.viewmodel.AuthViewModelProviderFactory
import id.alian.authappmvvm.utils.hide
import id.alian.authappmvvm.utils.show
import id.alian.authappmvvm.utils.snackbar
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(), AuthListener, KodeinAware {

    private val factory: AuthViewModelProviderFactory by instance()

    private lateinit var root: View
    private lateinit var progressBar: ProgressBar
    private lateinit var rootLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySignUpBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        val viewModel =
            ViewModelProvider(
                this,
                factory
            ).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

        rootLayout = binding.rootLayout
        progressBar = binding.progressBar
        root = View(this)

        viewModel.loginSession().observe(this, { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(user: User) {
        rootLayout.snackbar("${user.name} is logged in")
    }

    override fun onError(message: String) {
        progressBar.hide()
        rootLayout.snackbar(message)
    }

    override val kodein by kodein()
}