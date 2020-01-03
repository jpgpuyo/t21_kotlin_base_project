package com.worldline.t21kotlinbaseproject.domain.repository

import com.worldline.t21kotlinbaseproject.domain.model.User

/**
 * You can include all repository interfaces in this file or you can put them in different files
 */

interface UsersRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserDetail(id: Int): User
}