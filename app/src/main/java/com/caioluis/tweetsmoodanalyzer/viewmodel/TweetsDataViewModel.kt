package com.caioluis.tweetsmoodanalyzer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.caioluis.tweetsmoodanalyzer.base.BaseViewModel
import com.caioluis.tweetsmoodanalyzer.domain.GetTweetsDataUseCase
import com.caioluis.tweetsmoodanalyzer.domain.base.Response
import com.caioluis.tweetsmoodanalyzer.domain.entity.DomainTweetsList

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class TweetsDataViewModel(
    private val getTweetsDataUseCase: GetTweetsDataUseCase
) : BaseViewModel<DomainTweetsList>(getTweetsDataUseCase.receiveChannel) {

    private val tweetsLiveData: MutableLiveData<Response<DomainTweetsList>> = MutableLiveData()
    val tweetsObservableLiveData: LiveData<Response<DomainTweetsList>> = tweetsLiveData

    override fun handle(response: Response<DomainTweetsList>) {
        tweetsLiveData.postValue(response)
    }

    fun getTweets(userName: String) {
        getTweetsDataUseCase.invoke(userName)
    }
}