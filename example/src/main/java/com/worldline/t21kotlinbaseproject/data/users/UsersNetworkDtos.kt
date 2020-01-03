package com.worldline.t21kotlinbaseproject.data.users

import com.worldline.t21kotlinbaseproject.domain.model.User
import com.google.gson.annotations.SerializedName

data class UsersResponseDto(
    @SerializedName("page") val page : Int,
    @SerializedName("per_page") val per_page : Int,
    @SerializedName("total") val total : Int,
    @SerializedName("total_pages") val total_pages : Int,
    @SerializedName("data") val data : List<UserDto>
){
    fun toUserList(): List<User> = data.map { it.toUser() }
}

data class UserDetailResponseDto(
    @SerializedName("data") val data : UserDto
){
    fun toUser(): User = data.toUser()
}

data class UserDto (
    @SerializedName("id") val id : Int,
    @SerializedName("email") val email : String,
    @SerializedName("first_name") val first_name : String,
    @SerializedName("last_name") val last_name : String,
    @SerializedName("avatar") val avatar : String
){
    fun toUser(): User = User(
            id = id,
            email = email,
            fullName = "$first_name $last_name",
            avatar = avatar
    )
}