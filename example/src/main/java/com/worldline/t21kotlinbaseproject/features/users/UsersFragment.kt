package com.worldline.t21kotlinbaseproject.features.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.worldline.t21kotlinbaseproject.R
import com.worldline.t21kotlinbaseproject.core.extension.hideMe
import com.worldline.t21kotlinbaseproject.core.extension.showMe
import com.worldline.t21kotlinbaseproject.core.ui.RootFragment
import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.usecase.GetUsersUseCase
import kotlinx.android.synthetic.main.fragment_users.*
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class UsersFragment : RootFragment<UsersPresenter.View>(), UsersPresenter.View {

    override val presenter: UsersPresenter by instance()

    override val layoutResourceId: Int = R.layout.fragment_users

    override val fragmentModule: Kodein.Module = Kodein.Module("usersModule") {
        bind<UsersPresenter>() with provider {
            UsersPresenter(
                    getUsersUseCase = GetUsersUseCase(instance(), instance()),
                    errorHandler = instance()
            )
        }
    }

    private var adapter: UsersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun initializePresenter() {
        presenter.view = this
    }

    override fun initializeUI() {
        users.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter { presenter.onUserClicked(it) }
        users.adapter = adapter
    }

    override fun registerListeners() {
        // Nothing to do yet
    }

    override fun showProgress() = progressView.showMe()

    override fun hideProgress() = progressView.hideMe()

    override fun showUsers(users: List<User>) {
        adapter?.addAll(users.toMutableList())
    }

    override fun goToUserDetail(user: User) {
        navigator.goToUserDetail(this, user)
    }
}