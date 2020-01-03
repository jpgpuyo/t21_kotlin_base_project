package com.worldline.t21kotlinbaseproject.data.users

import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.repository.UsersRepository
import com.worldline.t21kotlinbaseproject.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsersRepositoryImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var usersRepository: UsersRepository

    @Before
    fun setUp() {
        usersRepository = UsersRepositoryImpl(usersNetworkDataSource = UsersNetworkDataSource())
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `get user list`() = runBlockingTest {

        val userList = usersRepository.getUsers()

        assertThat(userList).hasSize(6)
        assertThatUserIsValid(userList.first())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `get user detail`() = runBlockingTest {

        val user = usersRepository.getUserDetail(1)

        assertThatUserIsValid(user)
    }

    private fun assertThatUserIsValid(user: User) {
        assertThat(user)
            .isEqualToComparingFieldByField(expectedUser())
    }

    private fun expectedUser(): User {
       return User(id = 1, email = "george.bluth@reqres.in", fullName = "George Bluth", avatar = "https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg")
    }
}