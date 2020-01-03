package com.worldline.t21kotlinbaseproject.core.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.worldline.t21kotlinbaseproject.core.extension.toast
import com.worldline.t21kotlinbaseproject.navigation.Navigator
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.subKodein
import org.kodein.di.generic.instance

abstract class RootActivity<V : Presenter.View> : AppCompatActivity(), KodeinAware, Presenter.View {

    protected val navigator: Navigator by instance()

    abstract val presenter: Presenter<V>

    abstract val layoutResourceId: Int

    abstract val activityModule: Kodein.Module

    override val kodein by subKodein(kodein()) {
        import(activityModule)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)

        initializeUI()
        registerListeners()
    }

    override fun onStart() {
        super.onStart()
        initializePresenter()
        presenter.attach()
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }

    abstract fun initializePresenter()

    abstract fun initializeUI()

    abstract fun registerListeners()

    override fun showError(error: String) = toast(error)

    override fun showError(errorId: Int) = toast(errorId)

    override fun showMessage(message: String) = toast(message, Toast.LENGTH_SHORT)

    override fun showMessage(messageId: Int) = toast(messageId, Toast.LENGTH_SHORT)
}