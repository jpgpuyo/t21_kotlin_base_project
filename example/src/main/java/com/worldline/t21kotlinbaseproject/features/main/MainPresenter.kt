package com.worldline.t21kotlinbaseproject.features.main

import com.worldline.t21kotlinbaseproject.core.error.ErrorHandler
import com.worldline.t21kotlinbaseproject.core.ui.Presenter

class MainPresenter(
    errorHandler: ErrorHandler
) :
    Presenter<MainPresenter.View>(errorHandler = errorHandler) {

    override fun attach() {
    }

    fun onDisplayUsersClicked() = view?.goToUserList()

    interface View : Presenter.View {
        fun goToUserList()
    }
}