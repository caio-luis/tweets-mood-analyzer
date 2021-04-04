package com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment

import com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.entity.RemoteTextContent
import com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.entity.RemoteTextSentimentData
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
interface TextSentimentService {
    @POST("v1beta2/documents:analyzeSentiment")
    suspend fun getTextSentiment(
        @Body text: RemoteTextContent
    ): RemoteTextSentimentData
}