package com.worldline.t21kotlinbaseproject.core.ui

import com.worldline.t21kotlinbaseproject.core.error.ErrorHandler
import com.worldline.t21kotlinbaseproject.domain.model.Result

abstract class Presenter<V : Presenter.View>(
    protected val errorHandler: ErrorHandler
) {

    var view: V? = null

    abstract fun attach()

    open fun detach() {
        view = null
    }

    protected fun showErrorMessage(error: Result.Error){
        val message = errorHandler.convert(error)
        view?.showMessage(message)
    }

    interface View {

        fun showError(error: String)

        fun showError(errorId: Int)

        fun showMessage(message: String)

        fun showMessage(messageId: Int)
    }
}