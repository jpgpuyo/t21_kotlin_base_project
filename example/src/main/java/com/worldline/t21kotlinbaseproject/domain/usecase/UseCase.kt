package com.worldline.t21kotlinbaseproject.domain.usecase

import com.worldline.t21kotlinbaseproject.core.coroutines.CoroutineContextProvider
import com.worldline.t21kotlinbaseproject.domain.model.Result
import kotlinx.coroutines.*


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 *
 * @param Params Input parameters from the use case
 * @param ResultType Output type returned by the use case
 *
 */
abstract class UseCase<in Params, ResultType>(private val coroutineContextProvider: CoroutineContextProvider) {

    private var parentJob: Job? = null

    abstract suspend fun run(params: Params): ResultType

    operator fun invoke(params: Params, onResult: (Result<ResultType>) -> Unit = {}) {
        unsubscribe()
        parentJob = SupervisorJob()

        CoroutineScope(coroutineContextProvider.main + parentJob!!).launch {
            val result = withContext(coroutineContextProvider.IO) {
                try {
                    Result.Success(run(params))
                } catch (e: Exception) {
                    Result.Error(e)
                }
            }

            onResult(result)
        }
    }

    fun unsubscribe() {
        parentJob?.apply {
            cancelChildren()
            cancel()
        }
        parentJob = null
    }
}

class None