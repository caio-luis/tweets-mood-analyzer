package com.caioluis.tweetsmoodanalyzer.domain.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
abstract class BaseUseCase<in Parameters : Any, ResponseData> : CoroutineScope {

    private val parentJob = SupervisorJob()
    private val mainDispatcher = Dispatchers.Main
    private val backgroundDispatcher = Dispatchers.Default

    protected val responseChannel = Channel<Response<ResponseData>>()
    val receiveChannel: ReceiveChannel<Response<ResponseData>> = responseChannel

    override val coroutineContext: CoroutineContext
        get() = parentJob + mainDispatcher

    protected abstract suspend fun run(parameters: Parameters?)

    operator fun invoke(params: Parameters? = null) {
        launch(backgroundDispatcher) {
            run(params)
        }
    }

    fun clear() {
        responseChannel.close()
        parentJob.cancel()
    }
}