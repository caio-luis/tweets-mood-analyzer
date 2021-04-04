package com.caioluis.tweetsmoodanalyzer.domain

import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainSentimentScore
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainTextSentimentData
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainMeta
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweet
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweetsList

/**
 * Created by Caio Luis (caio-luis) on 04/04/21
 */
object MockedData {
    const val twitterUserNameParam = "twitter"

    val domainTweetsDataResponse = DomainTweetsList(
        tweets = listOf(
            DomainTweet(
                id = "12345678910123456",
                text = "This is a example of a tweet. Mocked data for testing purposes!"
            )
        ),
        meta = DomainMeta(
            newestId = 0,
            oldestId = 0,
            resultCount = 1,
            nextToken = ""
        )
    )

    const val textParamToAnalyze = "Example of text to analyze sentiment."

    val sentimentAnalysisResponse = DomainTextSentimentData(
        DomainSentimentScore(0.0, 0.0)
    )
}