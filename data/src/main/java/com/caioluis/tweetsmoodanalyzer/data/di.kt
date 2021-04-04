package com.caioluis.tweetsmoodanalyzer.data

import com.caioluis.tweetsmoodanalyzer.data.base.BaseUrls
import com.caioluis.tweetsmoodanalyzer.data.base.ServiceBuilder
import com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.TextSentimentService
import com.caioluis.tweetsmoodanalyzer.data.remote.text_sentiment.implementation.TextSentimentRepositoryImpl
import com.caioluis.tweetsmoodanalyzer.data.remote.tweets.TweetsDataService
import com.caioluis.tweetsmoodanalyzer.data.remote.tweets.implementation.TweetsDataRepositoryImpl
import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.repository.TextSentimentRepository
import com.caioluis.tweetsmoodanalyzer.domain.tweets.repository.TweetsDataRepository
import org.koin.dsl.module

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */

val dataModule = module {
    factory {
        ServiceBuilder<TweetsDataService>(
            baseUrl = BaseUrls.TWITTER_BASE_URL,
            authToken = BuildConfig.BEARER_TOKEN
        )
    }

    factory {
        ServiceBuilder<TextSentimentService>(
            baseUrl = BaseUrls.GOOGLE_BASE_URL,
            authToken = null
        )
    }

    factory<TweetsDataRepository> { TweetsDataRepositoryImpl(tweetsService = get()) }
    factory<TextSentimentRepository> { TextSentimentRepositoryImpl(textSentimentService = get()) }
}