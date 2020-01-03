package com.worldline.t21kotlinbaseproject.features.users.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.worldline.t21kotlinbaseproject.R
import com.worldline.t21kotlinbaseproject.core.extension.hideMe
import com.worldline.t21kotlinbaseproject.core.extension.load
import com.worldline.t21kotlinbaseproject.core.extension.showMe
import com.worldline.t21kotlinbaseproject.core.extension.toast
import com.worldline.t21kotlinbaseproject.core.ui.RootFragment
import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.usecase.GetUserDetailUseCase
import kotlinx.android.synthetic.main.fragment_user_detail.*
import kotlinx.android.synthetic.main.fragment_users.progressView
import kotlinx.android.synthetic.main.fragment_users_item_user.avatar
import kotlinx.android.synthetic.main.fragment_users_item_user.fullName
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider


class UserDetailFragment : RootFragment<UserDetailPresenter.View>(), UserDetailPresenter.View {

    override val presenter: UserDetailPresenter by instance()

    override val layoutResourceId: Int = R.layout.fragment_user_detail

    override val fragmentModule: Kodein.Module = Kodein.Module("userDetailModule") {
        bind<UserDetailPresenter>() with provider {
            UserDetailPresenter(
                    getUserDetailUseCase = GetUserDetailUseCase(instance(), instance()),
                    errorHandler = instance()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun initializePresenter() {
        presenter.view = this
    }

    override fun initializeUI() {

    }

    override fun registerListeners() {

    }

    override fun getUserId(): Int? {
        return arguments?.getInt("userId")
    }

    override fun showProgress() = progressView.showMe()

    override fun hideProgress() = progressView.hideMe()

    override fun showUser(user: User) {
        avatar.load(user.avatar)
        fullName.text = user.fullName
        email.text = user.email
        description.text = getString(R.string.loren_ipsum)
    }

    override fun showUserNotFound() {
        toast(getString(R.string.user_not_found_error))
    }
}
