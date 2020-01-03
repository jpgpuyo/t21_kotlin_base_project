package com.worldline.t21kotlinbaseproject.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface Executor {
    val main: CoroutineDispatcher
}