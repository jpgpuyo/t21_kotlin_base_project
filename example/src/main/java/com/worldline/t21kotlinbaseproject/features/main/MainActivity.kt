package com.worldline.t21kotlinbaseproject.features.main

import com.worldline.t21kotlinbaseproject.R
import com.worldline.t21kotlinbaseproject.core.extension.clickWithDebounce
import com.worldline.t21kotlinbaseproject.core.ui.RootActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MainActivity : RootActivity<MainPresenter.View>(),
        MainPresenter.View {

    override val presenter: MainPresenter by instance()

    override val layoutResourceId: Int = R.layout.activity_main

    override val activityModule: Kodein.Module = Kodein.Module("mainModule") {
        bind<MainPresenter>() with provider {
            MainPresenter(errorHandler = instance())
        }
    }

    override fun initializePresenter() {
        presenter.view = this
    }

    override fun initializeUI() {

    }

    override fun registerListeners() {
        displayUsersButton.clickWithDebounce { presenter.onDisplayUsersClicked() }
    }

    override fun goToUserList() {
        navigator.goToUserList(this)
    }
}
