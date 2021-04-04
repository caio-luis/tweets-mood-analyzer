package com.caioluis.tweetsmoodanalyzer.domain.tweets.usecase

import com.caioluis.tweetsmoodanalyzer.domain.MockedData
import com.caioluis.tweetsmoodanalyzer.domain.tweets.repository.TweetsDataRepository
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

/**
 * Created by Caio Luis (caio-luis) on 04/04/21
 */

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class GetTweetsDataUseCaseTest {

    private lateinit var tweetsDataUseCase: GetTweetsDataUseCase
    private var tweetsRepository: TweetsDataRepository = mockk(relaxed = true)

    @Before
    fun setup() {
        tweetsDataUseCase = GetTweetsDataUseCase(tweetsRepository)
    }

    @After
    fun clear() {
        unmockkAll()
    }

    @Test
    fun `assert that use case calls repository correctly`() = runBlockingTest {

        tweetsDataUseCase.executeBlocking(params = MockedData.twitterUserNameParam, scope = this)
        tweetsDataUseCase.receiveChannel.receive() // Loading
        tweetsDataUseCase.receiveChannel.receive() // Success

        coVerify {
            tweetsRepository.getTweetsData(MockedData.twitterUserNameParam)
        }
    }

    @Test
    fun `assert that repository returns correct data`() = runBlockingTest {
        coEvery {
            tweetsRepository.getTweetsData(MockedData.twitterUserNameParam)
        } returns MockedData.domainTweetsDataResponse

        tweetsDataUseCase.executeBlocking(params = MockedData.twitterUserNameParam, scope = this)
        tweetsDataUseCase.receiveChannel.receive()
        tweetsDataUseCase.receiveChannel.receive()
            .handleResponse(
                onSuccess = { payment ->
                    assertThat(payment)
                        .isEqualTo(MockedData.domainTweetsDataResponse)
                },
                onFailure = {
                    throw IllegalStateException("Wrong data type received by UseCase success.")
                }
            )
    }

    @Test
    fun `throw exception when param is a empty string`() = runBlockingTest {
        val exception = Exception("No user was found.")
        coEvery { tweetsRepository.getTweetsData(userName = "") } throws exception

        tweetsDataUseCase.executeBlocking(params = "", scope = this)
        tweetsDataUseCase.receiveChannel.receive()
        tweetsDataUseCase.receiveChannel.receive()
            .handleResponse(
                onFailure = { error ->
                    assertThat(error).isEqualTo(exception)
                }
            )
    }
}