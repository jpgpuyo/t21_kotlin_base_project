package com.worldline.t21kotlinbaseproject.navigation

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.worldline.t21kotlinbaseproject.R
import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.features.users.UsersActivity

/**
 * Class used to handle app navigation
 */

class Navigator {

    fun goToUserList(context: Context) = context.startActivity(UsersActivity.callingIntent(context))

    fun goToUserDetail(fragment: Fragment, user: User) {
        val bundle = bundleOf("userId" to user.id)
        fragment.findNavController().navigate(R.id.action_usersFragment_to_userDetailFragment, bundle)
    }
}