package com.caioluis.tweetsmoodanalyzer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.caioluis.tweetsmoodanalyzer.base.BaseViewModel
import com.caioluis.tweetsmoodanalyzer.domain.base.Response
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweetsList
import com.caioluis.tweetsmoodanalyzer.domain.tweets.usecase.GetTweetsDataUseCase
import com.caioluis.tweetsmoodanalyzer.exception.UserNotFoundException
import com.caioluis.tweetsmoodanalyzer.model.UiTweet
import com.caioluis.tweetsmoodanalyzer.model.toUi

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class TweetsDataViewModel(
    private val getTweetsDataUseCase: GetTweetsDataUseCase
) : BaseViewModel<DomainTweetsList>(getTweetsDataUseCase.receiveChannel) {

    private val tweetsLiveData: MutableLiveData<Response<List<UiTweet>>> = MutableLiveData()
    val tweetsObservableLiveData: LiveData<Response<List<UiTweet>>> = tweetsLiveData

    override fun handle(response: Response<DomainTweetsList>) {

        response.handleResponse(
            onLoading = { tweetsLiveData.postValue(Response.Loading) },
            onSuccess = {
                if (it.tweets.isNullOrEmpty())
                    tweetsLiveData.postValue(
                        Response.Failure(UserNotFoundException())
                    )
                else {
                    val uiTweets = it.tweets.map { tweets -> tweets.toUi() }
                    tweetsLiveData.postValue(Response.Success(uiTweets))
                }
            },
            onFailure = {
                tweetsLiveData.postValue(Response.Failure(it))
            }
        )
    }

    fun getTweets(userName: String) {
        getTweetsDataUseCase.invoke(userName)
    }
}