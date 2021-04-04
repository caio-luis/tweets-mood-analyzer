package com.caioluis.tweetsmoodanalyzer.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caioluis.tweetsmoodanalyzer.domain.base.Response
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweetsList
import com.caioluis.tweetsmoodanalyzer.domain.tweets.usecase.GetTweetsDataUseCase
import com.caioluis.tweetsmoodanalyzer.model.UiTweet
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
class TweetsDataViewModelTest {
    private val getTweetsDataUseCase: GetTweetsDataUseCase =
        mockk(relaxed = true)

    private val senderChannel: Channel<Response<DomainTweetsList>> = Channel()
    private lateinit var tweetsDataViewModel: TweetsDataViewModel

    @get:Rule
    val mainDispatcherCoroutineTestRule = MainDispatcherCoroutineTestRule()

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()

    @Before
    fun onEachTest() {
        every { getTweetsDataUseCase.receiveChannel } returns senderChannel
        tweetsDataViewModel = TweetsDataViewModel(getTweetsDataUseCase)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `assert that use case is invoked by the view model`() {
        tweetsDataViewModel.getTweets(Mocks.twitterUserNameParam)

        verify { getTweetsDataUseCase(Mocks.twitterUserNameParam) }
    }

    @Test
    fun `assert that observable on success receives a correct list of ui tweets`() {

        coEvery { getTweetsDataUseCase.invoke(Mocks.twitterUserNameParam) } coAnswers {

            val response = Response.Success(Mocks.domainTweetsDataResponse)

            senderChannel.send(response)
            Job()
        }

        var tweets: List<UiTweet>? = null

        tweetsDataViewModel.tweetsObservableLiveData.observeForever {
            it.handleResponse(onSuccess = { data -> tweets = data })
        }

        tweetsDataViewModel.getTweets(Mocks.twitterUserNameParam)

        Assertions.assertThat(tweets).isEqualTo(Mocks.uiTweetsDataResponse)
    }

    @Test
    fun `assert that observable on error receives an error`() {

        coEvery { getTweetsDataUseCase.invoke("") } coAnswers {

            val answer = Response.Failure(Exception())

            senderChannel.send(answer)
            Job()
        }

        var error: Throwable? = null

        tweetsDataViewModel.tweetsObservableLiveData.observeForever {
            it.handleResponse(onFailure = { errorSent -> error = errorSent })
        }

        tweetsDataViewModel.getTweets("")

        Assertions.assertThat(error).isInstanceOf(java.lang.Exception::class.java)
    }
}