package com.worldline.t21kotlinbaseproject.data.users

import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.model.UserNotFoundException
import com.worldline.t21kotlinbaseproject.domain.repository.UsersRepository

class UsersRepositoryImpl(private val usersNetworkDataSource: UsersNetworkDataSource) :
        UsersRepository {

    override suspend fun getUsers(): List<User> {
        val usersResponseDto = usersNetworkDataSource.users(1)
        return usersResponseDto?.toUserList()?: emptyList()
    }

    override suspend fun getUserDetail(id: Int): User {
        val userDetailResponseDto = usersNetworkDataSource.userDetail(id)
        if (userDetailResponseDto != null) {
            return userDetailResponseDto.toUser()
        } else {
            throw UserNotFoundException()
        }
    }
}