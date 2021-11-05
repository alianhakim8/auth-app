package id.alian.authappmvvm

import android.app.Application
import id.alian.authappmvvm.data.db.AppDatabase
import id.alian.authappmvvm.data.network.Api
import id.alian.authappmvvm.data.network.NetworkConnectionInterceptor
import id.alian.authappmvvm.data.repository.UserRepository
import id.alian.authappmvvm.ui.viewmodel.AuthViewModelProviderFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AuthApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@AuthApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { Api(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelProviderFactory(instance()) }
    }
}