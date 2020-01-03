package com.worldline.t21kotlinbaseproject.core.error

import com.worldline.t21kotlinbaseproject.domain.model.Result

/**
 * ErrorHandler.
 */
interface ErrorHandler {
    fun convert(error: Result.Error): String
}