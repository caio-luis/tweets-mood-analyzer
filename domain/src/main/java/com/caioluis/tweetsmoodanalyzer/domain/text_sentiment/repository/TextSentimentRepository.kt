package com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.repository

import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainTextSentimentData

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
interface TextSentimentRepository {
    suspend fun getTextSentiment(text: String): DomainTextSentimentData
}