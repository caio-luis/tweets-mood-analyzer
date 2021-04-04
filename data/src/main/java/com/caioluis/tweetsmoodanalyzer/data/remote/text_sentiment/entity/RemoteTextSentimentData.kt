package com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.entity

import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainTextSentimentData

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
data class RemoteTextSentimentData(
    val documentSentiment: RemoteSentimentScore
)

fun RemoteTextSentimentData.toDomain(): DomainTextSentimentData {
    return DomainTextSentimentData(documentSentiment.toDomain())
}