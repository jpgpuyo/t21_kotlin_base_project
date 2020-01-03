package com.worldline.t21kotlinbaseproject.core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.worldline.t21kotlinbaseproject.core.extension.toast
import com.worldline.t21kotlinbaseproject.navigation.Navigator
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.subKodein
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

abstract class RootFragment<V : Presenter.View> : Fragment(), KodeinAware, Presenter.View {

    protected val navigator: Navigator by instance()

    abstract val presenter: Presenter<V>

    abstract val layoutResourceId: Int

    abstract val fragmentModule: Kodein.Module

    override val kodein by subKodein(kodein()) {
        import(fragmentModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()
        registerListeners()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutResourceId, container, false)


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

    override fun showError(error: String) {
        toast(error)
    }

    override fun showError(errorId: Int) {
        toast(errorId)
    }

    override fun showMessage(message: String) {
        toast(message, Toast.LENGTH_SHORT)
    }

    override fun showMessage(messageId: Int) {
        toast(messageId, Toast.LENGTH_SHORT)
    }
}