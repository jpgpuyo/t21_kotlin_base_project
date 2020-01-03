package com.worldline.t21kotlinbaseproject.core.error

import android.content.Context
import com.worldline.t21kotlinbaseproject.R
import com.worldline.t21kotlinbaseproject.domain.model.Result
import com.worldline.t21kotlinbaseproject.domain.model.ServerErrorException

class AndroidErrorHandler(val context: Context) : ErrorHandler {
    override fun convert(error: Result.Error): String =
            when (error.exception) {
                is ServerErrorException -> context.getString(R.string.nointernet_error)
                else -> context.getString(R.string.default_error)
            }

}