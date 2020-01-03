package com.worldline.t21kotlinbaseproject.features.users

import android.view.View
import com.worldline.t21kotlinbaseproject.R
import com.worldline.t21kotlinbaseproject.core.extension.load
import com.worldline.t21kotlinbaseproject.core.ui.RootAdapter
import com.worldline.t21kotlinbaseproject.domain.model.User
import kotlinx.android.synthetic.main.fragment_users_item_user.view.*

class UsersAdapter(onItemClickListener: (User) -> Unit) :
        RootAdapter<User>(onItemClickListener = onItemClickListener) {

    override val itemLayoutId: Int = R.layout.fragment_users_item_user

    override fun viewHolder(view: View): RootViewHolder<User> =
            UsersViewHolder(view)

    class UsersViewHolder(view: View) : RootAdapter.RootViewHolder<User>(itemView = view) {
        override fun bind(model: User) {
            itemView.avatar.load(model.avatar)
            itemView.fullName.text = model.fullName
        }
    }
}
