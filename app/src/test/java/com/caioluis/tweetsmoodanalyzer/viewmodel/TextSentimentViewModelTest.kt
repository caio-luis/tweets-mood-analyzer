package com.caioluis.tweetsmoodanalyzer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caioluis.tweetsmoodanalyzer.domain.base.Response
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.GetTextSentimentAnalysisUseCase
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainTextSentimentData
import com.caioluis.tweetsmoodanalyzer.model.SentimentEmoji
import com.caioluis.tweetsmoodanalyzer.util.MainDispatcherCoroutineTestRule
import com.caioluis.tweetsmoodanalyzer.util.Mocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import org.assertj.core.api.Assertions
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Caio Luis (caio-luis) on 04/04/21
 */

@ExperimentalCoroutinesApi
class TextSentimentViewModelTest {
    private val getTextSentimentAnalysisUseCase: GetTextSentimentAnalysisUseCase =
        mockk(relaxed = true)

    private val senderChannel: Channel<Response<DomainTextSentimentData>> = Channel()
    private lateinit var textSentimentViewModel: TextSentimentViewModel

    @get:Rule
    val mainDispatcherCoroutineTestRule = MainDispatcherCoroutineTestRule()

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @Before
    fun onEachTest() {
        every { getTextSentimentAnalysisUseCase.receiveChannel } returns senderChannel
        textSentimentViewModel = TextSentimentViewModel(getTextSentimentAnalysisUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `assert that use case is invoked by the view model`() {
        textSentimentViewModel.sendTextToSentimentAnalysis("")

        verify { getTextSentimentAnalysisUseCase("") }
    }

    @Test
    fun `assert that observable on success receives a neutral emoji after analysis`() {

        coEvery { getTextSentimentAnalysisUseCase.invoke(Mocks.textParamToAnalyze) } coAnswers {

            val response = Response.Success(Mocks.neutralSentimentAnalysisResponse)

            senderChannel.send(response)
            Job()
        }

        var sentimentEmoji: SentimentEmoji? = null

        textSentimentViewModel.textSentimentObservable.observeForever {
            it.handleResponse(onSuccess = { data -> sentimentEmoji = data })
        }

        textSentimentViewModel.sendTextToSentimentAnalysis(Mocks.textParamToAnalyze)

        Assertions.assertThat(sentimentEmoji).isEqualTo(Mocks.neutralSentimentEmojiResponse)
    }

    @Test
    fun `assert that observable on success receives a smile emoji after analysis`() {

        coEvery { getTextSentimentAnalysisUseCase.invoke(Mocks.textParamToAnalyze) } coAnswers {

            val response = Response.Success(Mocks.happySentimentAnalysisResponse)

            senderChannel.send(response)
            Job()
        }

        var sentimentEmoji: SentimentEmoji? = null

        textSentimentViewModel.textSentimentObservable.observeForever {
            it.handleResponse(onSuccess = { data -> sentimentEmoji = data })
        }

        textSentimentViewModel.sendTextToSentimentAnalysis(Mocks.textParamToAnalyze)

        Assertions.assertThat(sentimentEmoji).isEqualTo(Mocks.smileSentimentEmojiResponse)
    }

    @Test
    fun `assert that observable on success receives a pensive emoji after analysis`() {

        coEvery { getTextSentimentAnalysisUseCase.invoke(Mocks.textParamToAnalyze) } coAnswers {

            val response = Response.Success(Mocks.sadSentimentAnalysisResponse)

            senderChannel.send(response)
            Job()
        }

        var sentimentEmoji: SentimentEmoji? = null

        textSentimentViewModel.textSentimentObservable.observeForever {
            it.handleResponse(onSuccess = { data -> sentimentEmoji = data })
        }

        textSentimentViewModel.sendTextToSentimentAnalysis(Mocks.textParamToAnalyze)

        Assertions.assertThat(sentimentEmoji).isEqualTo(Mocks.pensiveSentimentEmojiResponse)
    }

    @Test
    fun `assert that observable on error receives an error`() {

        coEvery { getTextSentimentAnalysisUseCase.invoke("") } coAnswers {

            val answer = Response.Failure(Exception())

            senderChannel.send(answer)
            Job()
        }

        var error: Throwable? = null

        textSentimentViewModel.textSentimentObservable.observeForever {
            it.handleResponse(onFailure = { errorSent -> error = errorSent })
        }

        textSentimentViewModel.sendTextToSentimentAnalysis("")

        Assertions.assertThat(error).isInstanceOf(java.lang.Exception::class.java)
    }
}