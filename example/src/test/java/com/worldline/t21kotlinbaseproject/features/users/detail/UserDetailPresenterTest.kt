package com.worldline.t21kotlinbaseproject.features.users.detail

import com.worldline.t21kotlinbaseproject.core.error.ErrorHandler
import com.worldline.t21kotlinbaseproject.domain.model.ServerErrorException
import com.worldline.t21kotlinbaseproject.domain.model.User
import com.worldline.t21kotlinbaseproject.domain.repository.UsersRepository
import com.worldline.t21kotlinbaseproject.domain.usecase.GetUserDetailUseCase
import com.worldline.t21kotlinbaseproject.utils.MainCoroutineRule
import com.worldline.t21kotlinbaseproject.utils.TestContextProvider
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserDetailPresenterTest {

    val usersRepository = mock<UsersRepository>()
    val errorHandler = mock<ErrorHandler>()
    val view = mock<UserDetailPresenter.View>()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var presenter: UserDetailPresenter

    @Before
    fun setUp() {
        presenter = UserDetailPresenter(GetUserDetailUseCase(usersRepository, coroutineContextProvider = TestContextProvider()), errorHandler = errorHandler)
        presenter.view = view
        whenever(view.getUserId()).thenReturn(1)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `show user happy path`() = mainCoroutineRule.runBlockingTest {
        val user = givenUser()
        whenever(usersRepository.getUserDetail(any())).thenReturn(user)

        presenter.attach()

        verify(view, times(1)).getUserId()
        verify(view, times(1)).showProgress()
        verify(view, times(1)).hideProgress()
        verify(view, times(1)).showUser(user)
        verifyNoMoreInteractions(view)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `show user server error exception`() = mainCoroutineRule.runBlockingTest {
        whenever(usersRepository.getUserDetail(1)).thenAnswer{ throw ServerErrorException() }
        whenever(errorHandler.convert(any())).thenReturn("errorMessage")

        presenter.attach()

        verify(view, times(1)).getUserId()
        verify(view, times(1)).showProgress()
        verify(view, times(1)).hideProgress()
        verify(view, times(1)).showUserNotFound()
        verifyNoMoreInteractions(view)
    }

    private fun givenUser(): User {
        return User(id = 1, fullName = "fullName", email = "email", avatar = "avatar")
    }
}