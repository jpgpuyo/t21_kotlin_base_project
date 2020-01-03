package com.worldline.t21kotlinbaseproject.features.users.detail

import com.worldline.t21kotlinbaseproject.core.error.ErrorHandler
import com.worldline.t21kotlinbaseproject.core.ui.Presenter
import com.worldline.t21kotlinbaseproject.domain.model.Result
import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.model.succeeded
import com.worldline.t21kotlinbaseproject.domain.usecase.GetUserDetailUseCase

class UserDetailPresenter(
        private val getUserDetailUseCase: GetUserDetailUseCase,
        errorHandler: ErrorHandler) :
    Presenter<UserDetailPresenter.View>(errorHandler = errorHandler) {

    override fun attach() {
        view?.showProgress()

        getUserDetailUseCase(
            params = GetUserDetailUseCase.Params(id = view?.getUserId()?:0),
            onResult = { result ->
                view?.hideProgress()
                if (result is Result.Success && result.succeeded) {
                    view?.showUser(result.data)
                } else if (result is Result.Error) {
                    view?.showUserNotFound()
                }
            }
        )
    }

    override fun detach() {
        super.detach()
        getUserDetailUseCase.unsubscribe()
    }

    interface View : Presenter.View {
        fun getUserId(): Int?
        fun showProgress()
        fun hideProgress()
        fun showUser(user: User)
        fun showUserNotFound()
    }
}