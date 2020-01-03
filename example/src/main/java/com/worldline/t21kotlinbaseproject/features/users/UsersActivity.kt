package com.worldline.t21kotlinbaseproject.features.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.worldline.t21kotlinbaseproject.R

class UsersActivity : AppCompatActivity() {

    companion object {
        fun callingIntent(context: Context) = Intent(context, UsersActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        setupToolbar()
    }

    private fun setupToolbar() {
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

                R.id.usersFragment -> {
                    supportActionBar?.apply {
                        setDisplayHomeAsUpEnabled(false)
                    }
                }

                R.id.userDetailFragment -> {
                    supportActionBar?.apply {
                        setDisplayHomeAsUpEnabled(true)
                    }
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> findNavController(R.id.nav_host_fragment).navigateUp()
            else -> super.onOptionsItemSelected(item)
        }
}