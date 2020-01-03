package com.worldline.t21kotlinbaseproject.domain.usecase

import com.worldline.t21kotlinbaseproject.core.coroutines.CoroutineContextProvider
import com.worldline.t21kotlinbaseproject.domain.model.*
import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.repository.UsersRepository

class GetUsersUseCase(
        private val usersRepository: UsersRepository,
        coroutineContextProvider: CoroutineContextProvider
) : UseCase<None, List<User>>(coroutineContextProvider = coroutineContextProvider) {

    override suspend fun run(params: None): List<User> = usersRepository.getUsers()

}