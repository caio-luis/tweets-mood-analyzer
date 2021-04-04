package com.caioluis.tweetsmoodanalyzer.domain.text_sentiment

import com.caioluis.tweetsmoodanalyzer.domain.MockedData
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.repository.TextSentimentRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
/**
 * Created by Caio Luis (caio-luis) on 04/04/21
 */
class GetTextSentimentAnalysisUseCaseTest {
    private lateinit var textSentimentUseCase: GetTextSentimentAnalysisUseCase
    private var textSentimentRepository: TextSentimentRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        textSentimentUseCase = GetTextSentimentAnalysisUseCase(textSentimentRepository)
    }

    @After
    fun clear() {
        unmockkAll()
    }

    @Test
    fun `assert that use case calls repository correctly`() = runBlockingTest {

        textSentimentUseCase.apply {
            executeBlocking(params = MockedData.textParamToAnalyze, scope = this)
            receiveChannel.receive() // Loading
            receiveChannel.receive() // Success
        }

        coVerify {
            textSentimentRepository.getTextSentiment(MockedData.textParamToAnalyze)
        }
    }

    @Test
    fun `assert that repository returns correct data`() = runBlockingTest {
        coEvery {
            textSentimentRepository.getTextSentiment(MockedData.textParamToAnalyze)
        } returns MockedData.sentimentAnalysisResponse

        val response = textSentimentUseCase.run {
            executeBlocking(params = MockedData.textParamToAnalyze, scope = this)
            receiveChannel.receive()
            receiveChannel.receive()
        }

        response.handleResponse(
            onSuccess = { payment ->
                assertThat(payment).isEqualTo(MockedData.sentimentAnalysisResponse)
            },
            onFailure = {
                throw IllegalStateException("Wrong data type received by UseCase success.")
            }
        )
    }

    @Test
    fun `throw exception when param is a empty string`() = runBlockingTest {
        val exception = Exception("No text to analyze.")
        coEvery { textSentimentRepository.getTextSentiment(text = "") } throws exception

        val response = textSentimentUseCase.run {
            executeBlocking(params = "", scope = this)
            receiveChannel.receive()
            receiveChannel.receive()
        }

        response.handleResponse(
            onFailure = { error ->
                assertThat(error).isEqualTo(exception)
            }
        )
    }
}