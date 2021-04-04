package com.caioluis.tweetsmoodanalyzer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.caioluis.tweetsmoodanalyzer.util.SentimentScoreChecker
import com.caioluis.tweetsmoodanalyzer.base.BaseViewModel
import com.caioluis.tweetsmoodanalyzer.domain.base.Response
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.SendTextToSentimentAnalysisUseCase
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainTextSentimentData
import com.caioluis.tweetsmoodanalyzer.model.SentimentEmoji

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class TextSentimentViewModel(
    private val textToSentimentAnalysisUseCase: SendTextToSentimentAnalysisUseCase
) : BaseViewModel<DomainTextSentimentData>(textToSentimentAnalysisUseCase.receiveChannel) {

    private val textSentimentLiveData: MutableLiveData<Response<SentimentEmoji>> =
        MutableLiveData()
    val textSentimentObservable: LiveData<Response<SentimentEmoji>> = textSentimentLiveData

    override fun handle(response: Response<DomainTextSentimentData>) {
        response.handleResponse(
            onLoading = {
                textSentimentLiveData.postValue(Response.Loading)
            },
            onSuccess = {
                val sentimentScore = it.documentSentiment.score
                val emoji = SentimentScoreChecker.getSentimentEmoji(sentimentScore)
                textSentimentLiveData.postValue(Response.Success(emoji))
            },
            onFailure = {
                textSentimentLiveData.postValue(Response.Failure(it))
            }
        )
    }

    fun sendTextToSentimentAnalysis(text: String) {
        textToSentimentAnalysisUseCase.invoke(text)
    }
}