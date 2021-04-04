package com.caioluis.tweetsmoodanalyzer.util

import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainSentimentScore
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.entity.DomainTextSentimentData
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainMeta
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweet
import com.caioluis.tweetsmoodanalyzer.domain.tweets.entity.DomainTweetsList
import com.caioluis.tweetsmoodanalyzer.model.SentimentEmoji
import com.caioluis.tweetsmoodanalyzer.model.UiTweet
import com.caioluis.tweetsmoodanalyzer.model.toUi

/**
 * Created by Caio Luis (caio-luis) on 04/04/21
 */
object Mocks {
    val neutralSentimentAnalysisResponse = DomainTextSentimentData(
        DomainSentimentScore(0.0, 0.0)
    )
    val happySentimentAnalysisResponse = DomainTextSentimentData(
        DomainSentimentScore(1.0, 1.0)
    )
    val sadSentimentAnalysisResponse = DomainTextSentimentData(
        DomainSentimentScore(-1.0, -1.0)
    )

    val neutralSentimentEmojiResponse = SentimentEmoji.Neutral
    val smileSentimentEmojiResponse = SentimentEmoji.Smile
    val pensiveSentimentEmojiResponse = SentimentEmoji.Pensive

    const val twitterUserNameParam = "twitter"
    const val textParamToAnalyze = "Example of text to analyze sentiment."

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

    val uiTweetsDataResponse: List<UiTweet> = domainTweetsDataResponse.tweets.map { it.toUi() }
}