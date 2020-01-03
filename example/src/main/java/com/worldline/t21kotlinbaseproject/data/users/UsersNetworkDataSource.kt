package com.worldline.t21kotlinbaseproject.data.users

import com.worldline.t21kotlinbaseproject.data.utils.RetrofitApiClient

class UsersNetworkDataSource(
        private val retrofitApiClient: RetrofitApiClient<UsersApiService> = getRetrofitApiClient(),
        private val usersApiService: UsersApiService = retrofitApiClient.createApiService()
) {
    fun users(page: Int) = retrofitApiClient.executeRequest(usersApiService.users(page))

    fun userDetail(id: Int) = retrofitApiClient.executeRequest(usersApiService.userDetail(id))
}

private fun getRetrofitApiClient() =
        RetrofitApiClient(
                UsersApiService::class.java,
                UsersApiService.BASE_URL
        )