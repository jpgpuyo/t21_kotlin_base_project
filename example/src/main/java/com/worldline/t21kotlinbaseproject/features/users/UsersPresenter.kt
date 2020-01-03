package com.worldline.t21kotlinbaseproject.features.users

import com.worldline.t21kotlinbaseproject.core.error.ErrorHandler
import com.worldline.t21kotlinbaseproject.core.ui.Presenter
import com.worldline.t21kotlinbaseproject.domain.model.Result
import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.model.succeeded
import com.worldline.t21kotlinbaseproject.domain.usecase.GetUsersUseCase
import com.worldline.t21kotlinbaseproject.domain.usecase.None

class UsersPresenter(
        private val getUsersUseCase: GetUsersUseCase,
        errorHandler: ErrorHandler
) :
    Presenter<UsersPresenter.View>(errorHandler = errorHandler) {

    override fun attach() {
        view?.showProgress()

        getUsersUseCase(
            params = None(),
            onResult = { result ->
                view?.hideProgress()
                if (result is Result.Success && result.succeeded) {
                    view?.showUsers(result.data)
                } else if (result is Result.Error) {
                    showErrorMessage(result)
                }
            }
        )
    }

    override fun detach() {
        super.detach()
        getUsersUseCase.unsubscribe()
    }

    fun onUserClicked(user: User) = view?.goToUserDetail(user)

    interface View : Presenter.View {
        fun showProgress()
        fun hideProgress()
        fun showUsers(users: List<User>)
        fun goToUserDetail(user: User)
    }
}