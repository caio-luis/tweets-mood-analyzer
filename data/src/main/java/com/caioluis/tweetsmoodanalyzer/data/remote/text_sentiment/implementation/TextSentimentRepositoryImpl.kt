package com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.implementation

import com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.TextSentimentService
import com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.entity.RemoteTextContent
import com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.entity.RemoteTextToAnalyze
import com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.entity.toDomain
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainTextSentimentData
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.repository.TextSentimentRepository

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class TextSentimentRepositoryImpl(
    private val textSentimentService: TextSentimentService
) : TextSentimentRepository {
    override suspend fun getTextSentiment(text: String): DomainTextSentimentData {
        return textSentimentService.getTextSentiment(
            RemoteTextContent(RemoteTextToAnalyze(text))
        ).toDomain()
    }
}