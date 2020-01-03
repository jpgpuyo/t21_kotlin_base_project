package com.worldline.t21kotlinbaseproject.features.main

import com.worldline.t21kotlinbaseproject.core.error.ErrorHandler
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.worldline.t21kotlinbaseproject.features.main.MainPresenter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    val errorHandler = mock<ErrorHandler>()
    val view = mock<MainPresenter.View>()

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        presenter = MainPresenter(errorHandler = errorHandler)
        presenter.view = view
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `go to user list when display users button is clicked`() {
        presenter.attach()
        presenter.onDisplayUsersClicked()

        verify(view, times(1)).goToUserList()
        verifyNoMoreInteractions(view)
    }
}