package com.worldline.t21kotlinbaseproject.data.users

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersApiService {

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }

    @GET("users")
    fun users(@Query("page") page:Int): Call<UsersResponseDto>

    @GET("users/{id}")
    fun userDetail(@Path("id") id: Int): Call<UserDetailResponseDto>
}