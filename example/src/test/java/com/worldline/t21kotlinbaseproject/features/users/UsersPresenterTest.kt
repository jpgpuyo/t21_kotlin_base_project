package com.worldline.t21kotlinbaseproject.features.users

import com.worldline.t21kotlinbaseproject.core.error.ErrorHandler
import com.worldline.t21kotlinbaseproject.domain.model.ServerErrorException
import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.repository.UsersRepository
import com.worldline.t21kotlinbaseproject.domain.usecase.GetUsersUseCase
import com.worldline.t21kotlinbaseproject.utils.MainCoroutineRule
import com.worldline.t21kotlinbaseproject.utils.TestContextProvider
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsersPresenterTest{

    val usersRepository = mock<UsersRepository>()
    val errorHandler = mock<ErrorHandler>()
    val view = mock<UsersPresenter.View>()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var presenter: UsersPresenter

    @Before
    fun setUp() {
        presenter = UsersPresenter(GetUsersUseCase(usersRepository, coroutineContextProvider = TestContextProvider()), errorHandler = errorHandler)
        presenter.view = view
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `show user list happy path`() = mainCoroutineRule.runBlockingTest {
        val userList = givenUserList()
        whenever(usersRepository.getUsers()).thenReturn(userList)

        presenter.attach()

        verify(view, times(1)).showProgress()
        verify(view, times(1)).hideProgress()
        verify(view, times(1)).showUsers(userList)
        verifyNoMoreInteractions(view)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `show user list server error exception`() = mainCoroutineRule.runBlockingTest {
        whenever(usersRepository.getUsers()).thenAnswer{ throw ServerErrorException() }
        whenever(errorHandler.convert(any())).thenReturn("errorMessage")

        presenter.attach()

        verify(view, times(1)).showProgress()
        verify(view, times(1)).hideProgress()
        verify(view, times(1)).showMessage("errorMessage")
        verifyNoMoreInteractions(view)
    }

    private fun givenUserList(): List<User> {
        return listOf(User(id = 1, fullName = "fullName", email = "email", avatar = "avatar"))
    }
}