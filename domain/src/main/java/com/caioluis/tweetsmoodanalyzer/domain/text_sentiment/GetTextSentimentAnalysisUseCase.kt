package com.caioluis.tweetsmoodanalyzer.domain.text_sentiment

import com.caioluis.tweetsmoodanalyzer.domain.base.BaseUseCase
import com.caioluis.tweetsmoodanalyzer.domain.base.Response
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainTextSentimentData
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.repository.TextSentimentRepository

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */
class GetTextSentimentAnalysisUseCase(
    private val textSentimentRepository: TextSentimentRepository
) : BaseUseCase<String, DomainTextSentimentData>() {
    override suspend fun run(parameters: String) {
        responseChannel.run {
            send(Response.Loading)

            val response = try {
                Response.Success(
                    textSentimentRepository.getTextSentiment(parameters)
                )
            } catch (ex: Exception) {
                Response.Failure(ex)
            }

            send(response)
        }
    }
}