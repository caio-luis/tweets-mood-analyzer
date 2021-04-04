package com.caioluis.tweetsmoodanalyzer.domain

import com.caioluis.tweetsmoodanalyzer.domain.text_sentiment.SendTextToSentimentAnalysisUseCase
import com.caioluis.tweetsmoodanalyzer.domain.tweets.usecase.GetTweetsDataUseCase
import org.koin.dsl.module

/**
 * Created by Caio Luis (caio-luis) on 02/04/21
 */

val domainModule = module {
    factory { GetTweetsDataUseCase(tweetsDataRepository = get()) }
    factory { SendTextToSentimentAnalysisUseCase(textSentimentRepository = get()) }
}