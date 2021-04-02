package com.caioluis.tweetsmoodanalyzer.domain.base

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
sealed class Response<out Data> {
    class Failure(val exception: Throwable) : Response<Nothing>()
    class Success<out Data>(val successData: Data) : Response<Data>()
    object Loading : Response<Nothing>()

    fun handleResponse(
        onLoading: () -> Unit = {},
        onSuccess: (Data) -> Unit = {},
        onFailure: (Throwable) -> Unit = {}
    ) {
        when (this) {
            is Loading -> onLoading()
            is Success -> onSuccess(successData)
            is Failure -> onFailure(exception)
        }
    }
}