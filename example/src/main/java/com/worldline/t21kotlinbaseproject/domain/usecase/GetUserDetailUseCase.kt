package com.worldline.t21kotlinbaseproject.domain.usecase

import com.worldline.t21kotlinbaseproject.core.coroutines.CoroutineContextProvider
import com.worldline.t21kotlinbaseproject.domain.model.*
import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.repository.UsersRepository

class GetUserDetailUseCase(
        private val usersRepository: UsersRepository,
        coroutineContextProvider: CoroutineContextProvider
) : UseCase<GetUserDetailUseCase.Params, User>(coroutineContextProvider = coroutineContextProvider) {

    override suspend fun run(params: Params): User = usersRepository.getUserDetail(params.id)

    data class Params(val id: Int)
}