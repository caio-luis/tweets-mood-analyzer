package com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.entity

import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainSentimentScore

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
data class RemoteSentimentScore(
    val magnitude: Double,
    val score: Double
)

fun RemoteSentimentScore.toDomain() = DomainSentimentScore(magnitude, score)