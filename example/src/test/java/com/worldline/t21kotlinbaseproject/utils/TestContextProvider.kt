package com.worldline.t21kotlinbaseproject.utils

import com.worldline.t21kotlinbaseproject.core.coroutines.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestContextProvider: CoroutineContextProvider() {
    override val main: CoroutineContext by lazy { Dispatchers.Unconfined }
    override val IO: CoroutineContext by lazy { Dispatchers.Unconfined }
}